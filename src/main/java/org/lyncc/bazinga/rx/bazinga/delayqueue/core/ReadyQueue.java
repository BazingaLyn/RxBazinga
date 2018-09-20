package org.lyncc.bazinga.rx.bazinga.delayqueue.core;

/**
 * 预备队列
 * @author liguolin
 * @create 2018-08-28 10:40
 **/
public interface ReadyQueue {

    /**
     * 将任务时间戳到期的task放入预备队列
     * @param topic
     * @param delayQueueJodId
     */
    void pushToReadyQueue(String topic, long delayQueueJodId);

    /**
     * 从预备队列中获取任务
     * @param topic
     * @return
     */
    Long pollFormReadyQueue(String topic);
}
