package org.lyncc.bazinga.rx.bazinga.delayqueue.core;

import com.google.common.collect.Lists;
import org.lyncc.bazinga.rx.bazinga.delayqueue.FgwGlobalExecutorHolder;
import org.lyncc.bazinga.rx.bazinga.delayqueue.core.redis.RedisDelayBucket;
import org.lyncc.bazinga.rx.bazinga.delayqueue.core.redis.RedisDelayQueueJobPool;
import org.lyncc.bazinga.rx.bazinga.delayqueue.core.redis.RedisReadyQueue;
import org.lyncc.bazinga.rx.bazinga.delayqueue.model.BucketItem;
import org.lyncc.bazinga.rx.bazinga.delayqueue.model.DelayQueueJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.lyncc.bazinga.rx.bazinga.delayqueue.core.DelayQueueJobResult.FAILEANDRETRY;
import static org.lyncc.bazinga.rx.bazinga.delayqueue.utils.DelayQueueUtils.getPlainIpStr;


/**
 * @author liguolin
 * @create 2018-08-27 17:50
 **/
public class DefaultDelayQueueManager implements DelayQueueManager {

    private final static Logger logger = LoggerFactory.getLogger(DefaultDelayQueueManager.class);

    /**
     * 1分钟 5分钟 15分钟 一个小时 6个小时 12个小时 24个小时
     */
    public static int[] time_interval = new int[]{60*1000,5*60*1000,15*60*1000,60*60*1000,6*60*60*1000,12*60*60*1000,24*60*60*1000};
    // 测试
//    public static int[] time_interval = new int[]{60*1000,2*60*1000,3*60*1000,4*60*1000,5*60*1000,6*60*1000,7*60*1000};

    public static final String DELAY_BUCKET_KEY_PREFIX                                  =                   "delayBucket";

    public static final String DELAY_QUEUE_THREAD_NAME                                  =                   "fgw-delay-queue-pool";

    public static final String PONG                                                     =                   "PONG";

    public static final int DELAY_BUCKET_NUM                                            =                   1;

    private static Map<String,DelayQueueHandler> topicDelayQueueHandlers                =                   new ConcurrentHashMap<String,DelayQueueHandler>(8);

    private DelayQueueJobPool delayQueueJobPool;

    private DelayBucket delayBucket;

    private ReadyQueue readyQueue;

    private Pool<Jedis> pool;

    private AtomicBoolean inited = new AtomicBoolean(false);

    public DefaultDelayQueueManager(DelayQueueJobPool delayQueueJobPool, DelayBucket delayBucket, ReadyQueue readyQueue) {
        this.delayQueueJobPool = delayQueueJobPool;
        this.delayBucket = delayBucket;
        this.readyQueue = readyQueue;
    }

    public DefaultDelayQueueManager(Pool<Jedis> pool) {
        this.pool = pool;
        if(null != pool){
            this.delayQueueJobPool = new RedisDelayQueueJobPool(pool);
            this.delayBucket = new RedisDelayBucket(pool);
            this.readyQueue = new RedisReadyQueue(pool);
        }
    }

    private void init() {

        logger.info(">>>>>>>>>>>>>>>invoke init");

        ExecutorService executorService = FgwGlobalExecutorHolder.getFgwCustomExecutors(DELAY_BUCKET_NUM,DELAY_QUEUE_THREAD_NAME);
        for (int i = 0; i < DELAY_BUCKET_NUM; i++) {
            executorService.execute(new DelayQueueRunner(getDelayBucketKey(i),this));
        }
    }


    @Override
    public void pushDelayQueueJob(DelayQueueJob delayQueueJob) {
        delayQueueJobPool.addDelayQueueJod(delayQueueJob);
        BucketItem item = new BucketItem(delayQueueJob.getDelayTime(),delayQueueJob.getId());
        delayBucket.addToBucket(getDelayBucketKey(delayQueueJob.getId()),item);
    }


    @Override
    public void invokeDelayQueueJob() {

        List<String> topics = Lists.newArrayList(topicDelayQueueHandlers.keySet());

        while (true){

            for (int i = 0; i < topics.size(); i++) {

                Long delayQueueJodId = readyQueue.pollFormReadyQueue(topics.get(i));
                if (delayQueueJodId == null) {
                    continue;
                }
                DelayQueueJob delayQueueJod = delayQueueJobPool.getDelayQueueJod(delayQueueJodId);
                if (delayQueueJod == null) {
                    continue;
                }
                DelayQueueHandler delayQueueHandler = topicDelayQueueHandlers.get(topics.get(i));
                if(null != delayQueueHandler){
                    DelayQueueJobResult delayQueueJobResult = FAILEANDRETRY;
                    try{
                        delayQueueJobResult = delayQueueHandler.doHandlerDelayQueueJob(delayQueueJod);
                        delayQueueHandler.delayQueueCallBack(delayQueueJod,delayQueueJobResult);
                    }catch (Exception e){
                        //ignore
                    }
                    switch (delayQueueJobResult){
                        case SUCCESS:
                            delayQueueJobPool.deleteDelayQueueJod(delayQueueJodId);
                            break;
                        case FAILEANDRETRY:
                            delayQueueJobPool.deleteDelayQueueJod(delayQueueJodId);
                            int count = delayQueueJod.getRetryCount();
                            // 最多尝试6次
                            if(count > 6){
                                break;
                            }
                            int index = count > 6 ? 6 : count;
                            long reDelayTime = System.currentTimeMillis()+time_interval[index];
                            delayQueueJod.setDelayTime(reDelayTime);
                            delayQueueJod.setRetryCount(++count);
                            delayQueueJobPool.addDelayQueueJod(delayQueueJod);
                            BucketItem item = new BucketItem(reDelayTime,delayQueueJod.getId());
                            delayBucket.addToBucket(getDelayBucketKey(delayQueueJod.getId()),item);
                            break;
                        case FAILEDANDBREAK:
                            delayQueueJobPool.deleteDelayQueueJod(delayQueueJodId);
                            break;
                        default:
                            break;
                    }

                }
            }
        }

    }

    @Override
    public void registerDelayQueueJobHandler(String topic, DelayQueueHandler delayQueueHandler) {
        topicDelayQueueHandlers.put(topic,delayQueueHandler);
    }

    @Override
    public void registerDelayQueueJobHandler(Map<String, DelayQueueHandler> delayQueueHandlerMap) {
        topicDelayQueueHandlers.putAll(delayQueueHandlerMap);
    }

    @Override
    public boolean isReady() {

        if(pool == null){
            return false;
        }

        Jedis jedis = pool.getResource();

        try{
            String ack = jedis.ping();
            jedis.close();
            if(PONG.equalsIgnoreCase(ack)){
                return true;
            }
        }catch (Exception e){
            jedis.close();
            logger.error("init jedis failed",e);
            return false;
        }

        return false;
    }

    @Override
    public void start() {

        if(topicDelayQueueHandlers.size() == 0){
            logger.info(">>>>>>>>>>>>>>>delayQueueHandlers is empty can't start");
            return;
        }
        // 如果不添加自定义的延迟队列处理器，则不增加EventLoop
        if (inited.compareAndSet(false, true)) {
            init();
        }
        //异步启动，不影响主进程
        CompletableFuture.runAsync(()->invokeDelayQueueJob(), Executors.newSingleThreadExecutor());
    }

    private static String getDelayBucketKey(long delayQueueJodId) {
        return String.format("%s::%s::%s",DELAY_BUCKET_KEY_PREFIX,getPlainIpStr(),Math.floorMod(delayQueueJodId,DELAY_BUCKET_NUM));
    }

    private static String getDelayBucketKey(int bucketId) {
        return String.format("%s::%s::%s",DELAY_BUCKET_KEY_PREFIX,getPlainIpStr(),bucketId);
    }

    public DelayQueueJobPool getDelayQueueJobPool() {
        return delayQueueJobPool;
    }

    public void setDelayQueueJobPool(DelayQueueJobPool delayQueueJobPool) {
        this.delayQueueJobPool = delayQueueJobPool;
    }

    public DelayBucket getDelayBucket() {
        return delayBucket;
    }

    public void setDelayBucket(DelayBucket delayBucket) {
        this.delayBucket = delayBucket;
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(ReadyQueue readyQueue) {
        this.readyQueue = readyQueue;
    }

}
