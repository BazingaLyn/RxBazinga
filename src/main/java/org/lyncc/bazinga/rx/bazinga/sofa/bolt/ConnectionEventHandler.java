package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author liguolin
 * @create 2018-07-27 17:02
 **/
@ChannelHandler.Sharable
public class ConnectionEventHandler extends ChannelDuplexHandler {


    private ConnectionManager       connectionManager;

    private ConnectionEventListener eventListener;

    private ConnectionEventExecutor eventExecutor;



    public class ConnectionEventExecutor {

        ExecutorService executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10000),
                new NamedThreadFactory("Bolt-conn-event-executor", true));
    }


}
