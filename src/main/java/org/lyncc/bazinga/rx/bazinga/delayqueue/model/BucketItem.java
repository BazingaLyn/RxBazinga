package org.lyncc.bazinga.rx.bazinga.delayqueue.model;

/**
 * @author liguolin
 * @create 2018-08-27 19:32
 **/
public class BucketItem {

    /**
     * 任务的执行时间
     */
    private long delayTime;

    /**
     * 延迟任务的唯一标识
     */
    private long delayQueueJodId;

    public BucketItem(long delayTime, long delayQueueJodId) {
        this.delayTime = delayTime;
        this.delayQueueJodId = delayQueueJodId;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public long getDelayQueueJodId() {
        return delayQueueJodId;
    }

    public void setDelayQueueJodId(long delayQueueJodId) {
        this.delayQueueJodId = delayQueueJodId;
    }
}
