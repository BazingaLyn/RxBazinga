package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.CommandEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author liguolin
 * @create 2018-07-21 20:54
 **/
public class RpcCommandEncoder implements CommandEncoder {

    private static final Logger logger = LoggerFactory.getLogger(RpcCommandEncoder.class);
    @Override
    public void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out) throws Exception {

        try{



        }catch (Exception e){
            logger.error("Exception caught!", e);
            throw e;
        }

    }
}
