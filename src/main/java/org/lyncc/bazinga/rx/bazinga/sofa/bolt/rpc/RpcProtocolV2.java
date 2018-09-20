package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.*;

/**
 * @author liguolin
 * @create 2018-07-18 17:29
 **/
public class RpcProtocolV2 implements Protocol {

    /* because the design defect, the version is neglected in RpcProtocol, so we design RpcProtocolV2 and add protocol version. */
    public static final byte PROTOCOL_CODE       = (byte) 2;
    /** version 1, is the same with RpcProtocol */
    public static final byte PROTOCOL_VERSION_1  = (byte) 1;
    /** version 2, is the protocol version for RpcProtocolV2 */
    public static final byte PROTOCOL_VERSION_2  = (byte) 2;

    @Override
    public CommandEncoder getEncoder() {
        return null;
    }

    @Override
    public CommandDecoder getDecoder() {
        return null;
    }

    @Override
    public HeartbeatTrigger getHeartbeatTrigger() {
        return null;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return null;
    }

    @Override
    public CommandFactory getCommandFactory() {
        return null;
    }
}
