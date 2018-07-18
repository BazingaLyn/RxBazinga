package org.lyncc.bazinga.rx.bazinga.netty4.srv.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import static org.lyncc.bazinga.rx.bazinga.netty4.srv.common.NettyCommonProtocol.ACK;
import static org.lyncc.bazinga.rx.bazinga.netty4.srv.common.NettyCommonProtocol.MAGIC;
import static org.lyncc.bazinga.rx.bazinga.netty4.srv.serializer.SerializerHolder.serializerImpl;


/**
 * 
 * @author BazingaLyn
 * @description ack的编码器
 * @time
 * @modifytime
 */
@ChannelHandler.Sharable
public class AcknowledgeEncoder extends MessageToByteEncoder<Acknowledge> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Acknowledge ack, ByteBuf out) throws Exception {
        byte[] bytes = serializerImpl().writeObject(ack);
        out.writeShort(MAGIC)
                .writeByte(ACK)
                .writeByte(0)
                .writeLong(ack.sequence())
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }
}
