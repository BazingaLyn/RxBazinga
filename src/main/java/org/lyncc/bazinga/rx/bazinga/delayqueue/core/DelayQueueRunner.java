package org.lyncc.bazinga.rx.bazinga.delayqueue.core;

import com.ihomefnt.fgw.delayqueue.model.BucketItem;
import com.ihomefnt.fgw.delayqueue.model.DelayQueueJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author liguolin
 * @create 2018-08-28 10:13
 **/
public class DelayQueueRunner implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(DelayQueueRunner.class);

    private String delayBucketKey;

    private DefaultDelayQueueManager defaultDelayQueueManager;


    public DelayQueueRunner(String delayBucketKey, DefaultDelayQueueManager defaultDelayQueueManager) {
        this.delayBucketKey = delayBucketKey;
        this.defaultDelayQueueManager = defaultDelayQueueManager;
    }

    @Override
    public void run() {

        while (true) {
            BucketItem bucketItem = defaultDelayQueueManager.getDelayBucket().getBucket(this.delayBucketKey);
            //没有任务
            if (bucketItem == null) {
                sleep();
                continue;
            }

            //延迟时间没到
            if (bucketItem.getDelayTime() > System.currentTimeMillis()) {
                sleep();
                continue;
            }

            DelayQueueJob delayQueueJod = defaultDelayQueueManager.getDelayQueueJobPool().getDelayQueueJod(bucketItem.getDelayQueueJodId());

            //延迟任务元数据不存在
            if (delayQueueJod == null) {
                defaultDelayQueueManager.getDelayBucket().deleteFormBucket(this.delayBucketKey,bucketItem);
                continue;
            }

            //再次确认延时时间是否到了
            if (delayQueueJod.getDelayTime() > System.currentTimeMillis()) {
                //删除旧的
                defaultDelayQueueManager.getDelayBucket().deleteFormBucket(this.delayBucketKey,bucketItem);
                //重新计算延迟时间
                defaultDelayQueueManager.getDelayBucket().addToBucket(this.delayBucketKey,new BucketItem(delayQueueJod.getId(),delayQueueJod.getDelayTime()));
            } else {
                defaultDelayQueueManager.getReadyQueue().pushToReadyQueue(delayQueueJod.getTopic(),delayQueueJod.getId());
                defaultDelayQueueManager.getDelayBucket().deleteFormBucket(this.delayBucketKey,bucketItem);
            }


        }

    }

    private void sleep(){
        try {
            TimeUnit.SECONDS.sleep(1L);
        }catch (InterruptedException e){
            logger.error("",e);
        }
    }
}
