package org.lyncc.bazinga.rx.bazinga.netty4.sample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * netty 客户端端处理器
 *
 * @author liguolin
 * @create 2018-01-17 11:32
 **/
public class HttpClientHandler extends SimpleClientHandler<FullHttpResponse> {

    HttpClientHandler(NettyClient nettyClient) {
        super(nettyClient);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {

    }
}
