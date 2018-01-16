package org.lyncc.bazinga.rx.bazinga.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import org.lyncc.bazinga.rx.bazinga.netty.model.RequestBody;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * http服务端处理类
 *
 * @author liguolin
 * @create 2018-01-16 16:20
 **/
public class HttpServerHandler extends SimpleServerHandler<FullHttpRequest> {


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println(String.format("Server io error: %s ",cause));

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) throws Exception {

        FullHttpResponse httpResponse;

        System.out.println(httpRequest.uri());

        String body = httpRequest.content().toString(CharsetUtil.UTF_8);

        RequestBody requestBody = JSON.parseObject(body,RequestBody.class);

        String serviceName = requestBody.getService();
        String methodName  = requestBody.getMethod();









    }
}
