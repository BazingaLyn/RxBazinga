package org.lyncc.bazinga.rx.bazinga.delayqueue;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.NamedThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * fgw全局线程执行器持有者
 *
 * @author liguolin
 * @create 2018-08-07 10:10
 **/
public class FgwGlobalExecutorHolder {


    private static Integer maxCpuCount =  Runtime.getRuntime().availableProcessors() * 2;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, maxCpuCount, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(16),
            new NamedThreadFactory("fgw-process-pool"));



    public static ThreadPoolExecutor getFgwGlobalExecutors(){
        return executor;
    }

    public static ThreadPoolExecutor getFgwCustomExecutors(int maxCpu,String threadName){
        return new ThreadPoolExecutor(1, maxCpu, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(16),
                new NamedThreadFactory(threadName));
    }

}
