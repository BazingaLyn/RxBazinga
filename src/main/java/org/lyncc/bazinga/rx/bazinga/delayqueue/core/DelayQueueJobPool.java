package org.lyncc.bazinga.rx.bazinga.delayqueue.core;


import org.lyncc.bazinga.rx.bazinga.delayqueue.model.DelayQueueJob;

/**
 * 延迟队列任务池
 * @author liguolin
 * @create 2018-08-27 17:49
 **/
public interface DelayQueueJobPool {


    /**
     * 将延迟队列加入到延迟队列中
     * @param delayQueueJob
     */
    void addDelayQueueJod(DelayQueueJob delayQueueJob);

    /**
     * 根据延迟队列id获取延迟任务信息
     * @param delayQueueJodId
     * @return
     */
    DelayQueueJob getDelayQueueJod(long delayQueueJodId);


    /**
     * 根据任务id删除任务信息
     * @param delayQueueJodId
     * @return
     */
    long deleteDelayQueueJod(long delayQueueJodId);
}
