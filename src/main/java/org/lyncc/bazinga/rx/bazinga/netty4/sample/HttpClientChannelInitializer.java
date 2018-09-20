package org.lyncc.bazinga.rx.bazinga.netty4.sample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

/**
 * netty客户端channel处理器初始化
 *
 * @author liguolin
 * @create 2018-01-17 11:27
 **/
public class HttpClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private NettyClient nettyClient;

    public HttpClientChannelInitializer(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline()
                // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                .addLast(new HttpResponseDecoder())
                .addLast(new HttpContentDecompressor())
                // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                .addLast(new HttpRequestEncoder())
                .addLast(new HttpObjectAggregator(Integer.MAX_VALUE))
                .addLast(new HttpClientHandler(nettyClient));
    }
}
