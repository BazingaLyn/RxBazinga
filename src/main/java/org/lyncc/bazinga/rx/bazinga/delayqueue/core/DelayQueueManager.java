package org.lyncc.bazinga.rx.bazinga.delayqueue.core;


import org.lyncc.bazinga.rx.bazinga.delayqueue.model.DelayQueueJob;

import java.util.Map;

/**
 * @author liguolin
 * @create 2018-08-27 17:37
 **/
public interface DelayQueueManager {

    /**
     * 保存延迟队列任务
     * @param delayQueueJob
     */
    void pushDelayQueueJob(DelayQueueJob delayQueueJob);


    /**
     * 获取已经到期的任务
     * @return
     */
    void invokeDelayQueueJob();


    /**
     * 注册延迟队列任务处理器
     * @param topic
     * @param delayQueueHandler
     */
    void registerDelayQueueJobHandler(String topic, DelayQueueHandler delayQueueHandler);


    /**
     * 注册延迟队列任务处理器
     * @param delayQueueHandlerMap
     */
    void registerDelayQueueJobHandler(Map<String, DelayQueueHandler> delayQueueHandlerMap);


    /**
     * 启动延迟队列任务
     */
    void start();


    /**
     * 是否准备完成
     * @return
     */
    boolean isReady();

}
