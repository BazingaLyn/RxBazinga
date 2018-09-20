package org.lyncc.bazinga.rx.bazinga.delayqueue.core.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.ihomefnt.fgw.delayqueue.core.DelayQueueJobPool;
import com.ihomefnt.fgw.delayqueue.model.DelayQueueJob;
import com.ihomefnt.fgw.delayqueue.utils.DelayQueueUtils;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * @author liguolin
 * @create 2018-08-27 17:50
 **/
public class RedisDelayQueueJobPool implements DelayQueueJobPool {

    private static final String DELAY_QUEUE_JOB_POOL = "delayQueueJobPool::"+ DelayQueueUtils.getPlainIpStr();

    private Pool<Jedis> pool;

    public RedisDelayQueueJobPool(Pool<Jedis> pool) {
        this.pool = pool;
    }

    @Override
    public void addDelayQueueJod(DelayQueueJob delayQueueJob) {
        Jedis jedis = pool.getResource();
        jedis.hset(DELAY_QUEUE_JOB_POOL,delayQueueJob.getId()+"", JSON.toJSONString(delayQueueJob));
        jedis.close();
    }

    @Override
    public DelayQueueJob getDelayQueueJod(long delayQueueJodId) {
        Jedis jedis = pool.getResource();
        String result =  jedis.hget(DELAY_QUEUE_JOB_POOL,delayQueueJodId+"");
        jedis.close();
        if(Strings.isNullOrEmpty(result)){
            return null;
        }
        return JSON.parseObject(result,DelayQueueJob.class);
    }

    @Override
    public long deleteDelayQueueJod(long delayQueueJodId) {
        Jedis jedis = pool.getResource();
        long deleted =  jedis.hdel(DELAY_QUEUE_JOB_POOL,delayQueueJodId+"");
        jedis.close();
        return deleted;
    }
}
