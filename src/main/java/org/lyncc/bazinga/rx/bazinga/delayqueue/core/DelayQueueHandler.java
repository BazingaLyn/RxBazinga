package org.lyncc.bazinga.rx.bazinga.delayqueue.core;


import org.lyncc.bazinga.rx.bazinga.delayqueue.model.DelayQueueJob;

/**
 * @author liguolin
 * @create 2018-08-28 9:57
 **/
public interface DelayQueueHandler {

    /**
     * 处理到期的定时任务
     * @param delayQueueJob
     * @return
     */
    DelayQueueJobResult doHandlerDelayQueueJob(DelayQueueJob delayQueueJob);


    /**
     * 处理回调结果
     * @param delayQueueJob
     * @param delayQueueJobResult
     */
    void delayQueueCallBack(DelayQueueJob delayQueueJob, DelayQueueJobResult delayQueueJobResult);
}
