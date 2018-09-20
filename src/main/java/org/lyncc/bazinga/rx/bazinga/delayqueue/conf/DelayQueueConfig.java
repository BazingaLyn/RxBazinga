package org.lyncc.bazinga.rx.bazinga.delayqueue.conf;

import org.lyncc.bazinga.rx.bazinga.delayqueue.core.DefaultDelayQueueManager;
import org.lyncc.bazinga.rx.bazinga.delayqueue.core.DelayQueueManager;
import org.lyncc.bazinga.rx.bazinga.delayqueue.springsupport.DelayQueueSpringSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * @author liguolin
 * @create 2018-08-29 14:10
 **/
@Configuration
public class DelayQueueConfig{

    @Bean
    public DelayQueueSpringSupport createDelayQueueSpringSupport(){
        return new DelayQueueSpringSupport();
    }


    @Bean(name = "delayQueueManager")
    public DelayQueueManager delayQueueManager(Pool<Jedis> pool){
        return new DefaultDelayQueueManager(pool);
    }
}
