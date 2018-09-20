package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc.protocol.RpcProtocolManager;

/**
 * @author liguolin
 * @create 2018-07-22 10:11
 **/
public abstract class RpcRemoting extends BaseRemoting {

    static {
        RpcProtocolManager.initProtocols();
    }

    protected RemotingAddressParser    addressParser;

    /** connection manager */
    protected DefaultConnectionManager connectionManager;


    public RpcRemoting(CommandFactory commandFactory) {
        super(commandFactory);
    }

    public RpcRemoting(CommandFactory commandFactory, RemotingAddressParser addressParser,
                       DefaultConnectionManager connectionManager) {
        this(commandFactory);
        this.addressParser = addressParser;
        this.connectionManager = connectionManager;
    }

    @Override
    protected InvokeFuture createInvokeFuture(RemotingCommand request, InvokeContext invokeContext) {


        return null;
    }


}
