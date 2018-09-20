package org.lyncc.bazinga.rx.bazinga.delayqueue.core;

/**
 * @author liguolin
 * 延迟队列客户端处理结果
 */
public enum DelayQueueJobResult {

    /**
     * 延迟队列任务处理成功(需要删除{@link DelayQueueJobPool}和{@link DelayBucket}中的信息)
     */
    SUCCESS,

    /**
     * 延迟队列任务处理失败需要再次重试
     */
    FAILEANDRETRY,

    /**
     * 延迟队列任务处理失败不再重试(需要删除{@link DelayQueueJobPool}和{@link DelayBucket}中的信息)
     */
    FAILEDANDBREAK;



}
