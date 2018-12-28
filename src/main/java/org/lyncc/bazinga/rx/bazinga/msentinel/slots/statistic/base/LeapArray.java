package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.base;


import org.lyncc.bazinga.rx.bazinga.msentinel.util.AssertUtil;
import org.lyncc.bazinga.rx.bazinga.msentinel.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liguolin
 * @create 2018-12-25 19:26
 **/
public abstract class LeapArray<T> {

    protected int windowLengthInMs;
    protected int sampleCount;
    protected int intervalInMs;

    private final ReentrantLock updateLock = new ReentrantLock();

    protected final AtomicReferenceArray<WindowWrap<T>> array;

    public LeapArray(int sampleCount,int intervalInMs){

        AssertUtil.isTrue(sampleCount > 0, "bucket count is invalid: " + sampleCount);
        AssertUtil.isTrue(intervalInMs > 0, "total time interval of the sliding window should be positive");
        AssertUtil.isTrue(intervalInMs % sampleCount == 0, "time span needs to be evenly divided");

        this.windowLengthInMs = intervalInMs / sampleCount;
        this.intervalInMs = intervalInMs;
        this.sampleCount = sampleCount;

        this.array = new AtomicReferenceArray<WindowWrap<T>>(sampleCount);
    }



    public List<WindowWrap<T>> list(){
        int size = array.length();
        List<WindowWrap<T>> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            WindowWrap<T> windowWrap = array.get(i);
            if(null == windowWrap || isWindowDeprecated(windowWrap)){
                continue;
            }
            result.add(windowWrap);
        }

        return result;

    }

    private boolean isWindowDeprecated(WindowWrap<T> windowWrap) {
        return TimeUtil.currentTimeMillis() - windowWrap.windowStart() > intervalInMs;
    }


    public WindowWrap<T> currentWindow(){
        return currentWindow(TimeUtil.currentTimeMillis());
    }

    private WindowWrap<T> currentWindow(long timeMillis) {

        if(timeMillis < 0){
            return null;
        }

        int idx = caculateTimeIdx(timeMillis);

        long windowStart = caculateWindowStart(timeMillis);

        while (true){
            WindowWrap<T> old = array.get(idx);
            if(old == null){
                WindowWrap<T> window = new WindowWrap<T>(windowLengthInMs,windowStart,newEmptyBucket());

                if(array.compareAndSet(idx,null,window)){
                    return window;
                }else{
                    Thread.yield();
                }
            } else if(windowStart == old.windowStart()){
                return old;
            }else if(windowStart > old.windowStart()){
                if(updateLock.tryLock()){
                    try{
                        return resetWindowTo(old,windowStart);
                    }finally {
                        updateLock.unlock();
                    }
                }else {
                    Thread.yield();
                }
            }else if(windowStart < old.windowStart()){
                return new WindowWrap<T>(windowLengthInMs, windowStart, newEmptyBucket());
            }
        }
    }

    public List<T> values() {
        int size = array.length();
        List<T> result = new ArrayList<T>(size);

        for (int i = 0; i < size; i++) {
            WindowWrap<T> windowWrap = array.get(i);
            if (windowWrap == null || isWindowDeprecated(windowWrap)) {
                continue;
            }
            result.add(windowWrap.value());
        }
        return result;
    }

    protected abstract WindowWrap<T> resetWindowTo(WindowWrap<T> old, long windowStart);

    protected abstract T newEmptyBucket();

    private long caculateWindowStart(long timeMillis) {
        return timeMillis - timeMillis % windowLengthInMs;

    }

    protected int caculateTimeIdx(long timeMillis) {
        long timeId = timeMillis / windowLengthInMs;
        return (int)(timeId % this.array.length());


    }


}
