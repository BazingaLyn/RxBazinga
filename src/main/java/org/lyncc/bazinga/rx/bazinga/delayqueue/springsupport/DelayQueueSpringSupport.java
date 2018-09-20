package org.lyncc.bazinga.rx.bazinga.delayqueue.springsupport;

import com.ihomefnt.fgw.delayqueue.core.DelayQueueHandler;
import com.ihomefnt.fgw.delayqueue.core.DelayQueueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.testng.util.Strings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 基于Redis 延迟队列spring的简单支持
 * 如果后期做的够好，够好用，可以对spring schema支持和spring boot autoconfig的进行支持
 * @author liguolin
 * @create 2018-08-29 11:49
 **/
public class DelayQueueSpringSupport implements ApplicationContextAware,BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DelayQueueSpringSupport.class);


    private ApplicationContext applicationContext;

    private DelayQueueManager delayQueueManager;

    private Map<String,DelayQueueHandler> collectionMap = new ConcurrentHashMap<>();

    private AtomicBoolean inited = new AtomicBoolean(false);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public void start(){

        if(collectionMap.size() == 0){
            logger.info("DelayQueueSpringSupport ready begin but delayQueueSize is empty return");
            return;
        }

        delayQueueManager.registerDelayQueueJobHandler(collectionMap);

        if(!delayQueueManager.isReady()){
            logger.info("delayQueueManager is not ok please check redis config");
            return;
        }
        delayQueueManager.start();

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof DelayQueueHandler){
            DelayHandler delayHandler = applicationContext.findAnnotationOnBean(beanName,DelayHandler.class);
            if(null != delayHandler && !Strings.isNullOrEmpty(delayHandler.topic())){
                collectionMap.put(delayHandler.topic(),(DelayQueueHandler)bean);
            }
        }
        return bean;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (inited.compareAndSet(false, true)) {

            logger.info("=====>fgw spring context refreshed ok");

            DelayQueueManager delayQueueManager = applicationContext.getBean("delayQueueManager",DelayQueueManager.class);

            setDelayQueueManager(delayQueueManager);

            start();
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    public void setDelayQueueManager(DelayQueueManager delayQueueManager) {
        this.delayQueueManager = delayQueueManager;
    }
}
