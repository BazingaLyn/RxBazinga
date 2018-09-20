package org.lyncc.bazinga.rx.bazinga.netty4.sample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务处理类的抽象类
 *
 * @author liguolin
 * @create 2018-01-16 16:21
 **/
public abstract class SimpleServerHandler<T> extends SimpleChannelInboundHandler<T> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelActive :" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channelInactive :" + ctx.channel().remoteAddress());
    }

    @Override
    public abstract void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception;

}
