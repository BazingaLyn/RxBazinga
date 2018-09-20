package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc;

import io.netty.channel.ChannelHandlerContext;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.CommandFactory;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.HeartbeatTrigger;

/**
 * @author liguolin
 * @create 2018-07-21 21:43
 **/
public class RpcHeartbeatTrigger implements HeartbeatTrigger {

    private CommandFactory  commandFactory;

    public RpcHeartbeatTrigger(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }


    @Override
    public void heartbeatTriggered(ChannelHandlerContext ctx) throws Exception {

    }
}
