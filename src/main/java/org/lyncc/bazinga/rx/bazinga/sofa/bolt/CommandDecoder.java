package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @author liguolin
 * @create 2018-07-18 16:56
 **/
public interface CommandDecoder {

    void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception;
}
