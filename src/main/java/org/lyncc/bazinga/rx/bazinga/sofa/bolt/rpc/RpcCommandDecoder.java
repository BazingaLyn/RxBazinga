package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.CommandDecoder;

import java.util.List;

/**
 * @author liguolin
 * @create 2018-07-21 21:41
 **/
public class RpcCommandDecoder implements CommandDecoder {


    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    }
}
