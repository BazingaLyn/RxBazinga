package org.lyncc.bazinga.rx.bazinga.netty4.srv.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static org.lyncc.bazinga.rx.bazinga.netty4.srv.common.NettyCommonProtocol.MAGIC;
import static org.lyncc.bazinga.rx.bazinga.netty4.srv.serializer.SerializerHolder.serializerImpl;

/**
 * @author liguolin
 * @create 2018-07-16 18:03
 **/
@ChannelHandler.Sharable
public class MessageEncoder extends MessageToByteEncoder<Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        byte[] bytes = serializerImpl().writeObject(msg);
        out.writeShort(MAGIC)
                .writeByte(msg.sign())
                .writeByte(0)
                .writeLong(0)
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }
}
