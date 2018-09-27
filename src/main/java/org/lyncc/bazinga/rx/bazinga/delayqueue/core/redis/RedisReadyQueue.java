package org.lyncc.bazinga.rx.bazinga.delayqueue.core.redis;

import org.lyncc.bazinga.rx.bazinga.delayqueue.core.ReadyQueue;
import org.lyncc.bazinga.rx.bazinga.delayqueue.utils.DelayQueueUtils;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.List;

/**
 * @author liguolin
 * @create 2018-08-28 10:46
 **/
public class RedisReadyQueue implements ReadyQueue {


    private static final String READY_QUEUE_JOB_POOL = "redisReadyQueue::"+ DelayQueueUtils.getPlainIpStr();

    private Pool<Jedis> pool;

    public RedisReadyQueue(Pool<Jedis> pool) {
        this.pool = pool;
    }

    @Override
    public void pushToReadyQueue(String topic, long delayQueueJodId) {
        Jedis jedis = pool.getResource();
        jedis.lpush(getReadyQueueName(topic),delayQueueJodId+"");
        jedis.close();
    }

    @Override
    public Long pollFormReadyQueue(String topic) {
        Jedis jedis = pool.getResource();
        List<String> result =  jedis.brpop(5,getReadyQueueName(topic));
        jedis.close();
        if(!CollectionUtils.isEmpty(result)){
            return Long.parseLong(result.get(1));
        }
        return null;
    }

    public static String getReadyQueueName(String topic){
        return String.format("%s::%s",READY_QUEUE_JOB_POOL,topic);
    }
}
