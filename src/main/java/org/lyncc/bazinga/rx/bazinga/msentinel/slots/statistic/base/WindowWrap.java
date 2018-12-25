package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.base;

/**
 * @author liguolin
 * @create 2018-12-25 19:30
 **/
public class WindowWrap<T> {

    private final long windowLengthInMs;

    private long windowStart;

    private T value;

    public WindowWrap(long windowLengthInMs, long windowStart, T value) {
        this.windowLengthInMs = windowLengthInMs;
        this.windowStart = windowStart;
        this.value = value;
    }

    public long windowLength(){
        return windowLengthInMs;
    }

    public long windowStart(){
        return windowStart;
    }

    public T value(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }


}
