package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;

/**
 * @author liguolin
 * @create 2018-07-18 16:54
 **/
public interface CommandEncoder {

    void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out) throws Exception;
}
