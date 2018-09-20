package org.lyncc.bazinga.rx.bazinga.delayqueue.core;

import com.ihomefnt.fgw.delayqueue.model.BucketItem;

/**
 * 延迟队列的bucket
 * @author liguolin
 * @create 2018-08-27 17:46
 **/
public interface DelayBucket {


    /**
     *
     * @param delayBucketKey
     * @param item
     */
    void addToBucket(String delayBucketKey, BucketItem item);

    /**
     *
     * @param delayBucketKey
     * @return
     */
    BucketItem getBucket(String delayBucketKey);

    /**
     *
     * @param delayBucketKey
     * @param bucketItem
     */
    void deleteFormBucket(String delayBucketKey, BucketItem bucketItem);
}
