package org.lyncc.bazinga.rx.bazinga.netty4.sample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.LongAdder;

/**
 * netty 客户端抽象处理器
 *
 * @author liguolin
 * @create 2018-01-17 11:33
 **/
public abstract class SimpleClientHandler<T> extends SimpleChannelInboundHandler<T> {

    protected NettyConfig nettyConfig;

    protected volatile Channel channel;

    protected NettyClient nettyClient;

    protected static boolean isShutdown;

    protected LongAdder hits = new LongAdder();


    public SimpleClientHandler(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel active: "+ this.channel);
        super.channelActive(ctx);
    }


    public NettyConfig getNettyConfig() {
        return nettyConfig;
    }

    public void setNettyConfig(NettyConfig nettyConfig) {
        this.nettyConfig = nettyConfig;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public NettyClient getNettyClient() {
        return nettyClient;
    }

    public void setNettyClient(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    public static boolean isIsShutdown() {
        return isShutdown;
    }

    public static void setIsShutdown(boolean isShutdown) {
        SimpleClientHandler.isShutdown = isShutdown;
    }

    public LongAdder getHits() {
        return hits;
    }

    public void setHits(LongAdder hits) {
        this.hits = hits;
    }
}
