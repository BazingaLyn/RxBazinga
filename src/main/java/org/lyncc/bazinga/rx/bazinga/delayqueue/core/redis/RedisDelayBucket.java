package org.lyncc.bazinga.rx.bazinga.delayqueue.core.redis;

import org.lyncc.bazinga.rx.bazinga.delayqueue.core.DelayBucket;
import org.lyncc.bazinga.rx.bazinga.delayqueue.model.BucketItem;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.util.Pool;

import java.util.Set;

/**
 * @author liguolin
 * @create 2018-08-27 17:48
 **/
public class RedisDelayBucket implements DelayBucket {

    private Pool<Jedis> pool;

    public RedisDelayBucket(Pool<Jedis> pool) {
        this.pool = pool;
    }

    @Override
    public void addToBucket(String delayBucketKey, BucketItem item) {
        Jedis jedis = pool.getResource();
        jedis.zadd(delayBucketKey,(double)item.getDelayTime(),item.getDelayQueueJodId()+"");
        jedis.close();


    }

    @Override
    public BucketItem getBucket(String delayBucketKey) {
        Jedis jedis = pool.getResource();
        Set<Tuple> tuples = jedis.zrangeWithScores(delayBucketKey,0,0);
        jedis.close();
        if(!CollectionUtils.isEmpty(tuples)){
            Tuple tuple = tuples.stream().findFirst().get();
            return new BucketItem(new Double(tuple.getScore()).longValue(),Long.parseLong(new String(tuple.getBinaryElement())));
        }
        return null;
    }

    @Override
    public void deleteFormBucket(String delayBucketKey, BucketItem bucketItem) {
        Jedis jedis = pool.getResource();
        jedis.zrem(delayBucketKey,bucketItem.getDelayQueueJodId()+"");
        jedis.close();
    }
}
