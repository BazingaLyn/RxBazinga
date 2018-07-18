package org.lyncc.bazinga.rx.bazinga.netty4.srv.connector;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.PlatformDependent;

import java.util.concurrent.ThreadFactory;

/**
 * @author liguolin
 * @create 2018-07-17 13:49
 **/
public abstract class NettyClientConnector implements ClientConnector {

    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private Bootstrap bootstrap;
    private EventLoopGroup worker;
    private int nWorkers;

    protected volatile ByteBufAllocator allocator;

    public NettyClientConnector() {
        this(AVAILABLE_PROCESSORS << 1);
    }

    public NettyClientConnector(int nWorkers) {
        this.nWorkers = nWorkers;
    }

    protected void init(){

        ThreadFactory workerFactory = new DefaultThreadFactory("client.connector");
        worker = initEventLoopGroup(nWorkers, workerFactory);

        bootstrap = new Bootstrap().group(worker);

        allocator = new PooledByteBufAllocator(PlatformDependent.directBufferPreferred());
    }

    protected Bootstrap bootstrap() {
        return bootstrap;
    }

    protected Object bootstrapLock() {
        return bootstrap;
    }


    @Override
    public void shutdownGracefully() {
        worker.shutdownGracefully();
    }

    protected abstract EventLoopGroup initEventLoopGroup(int nWorkers, ThreadFactory workerFactory);
}
