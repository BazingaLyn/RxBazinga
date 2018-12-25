package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.base;

import com.alibaba.csp.sentinel.util.AssertUtil;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author liguolin
 * @create 2018-12-25 19:26
 **/
public abstract class LeapArray<T> {

    protected int windowLengthInMs;
    protected int sampleCount;
    protected int intervalInMs;

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


}
