package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc.protocol;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.ProtocolManager;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc.RpcProtocol;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc.RpcProtocolV2;

/**
 * @author liguolin
 * @create 2018-07-22 10:38
 **/
public class RpcProtocolManager {

    public static final int DEFAULT_PROTOCOL_CODE_LENGTH = 1;

    public static void initProtocols() {
        ProtocolManager.registerProtocol(new RpcProtocol(), RpcProtocol.PROTOCOL_CODE);
        ProtocolManager.registerProtocol(new RpcProtocolV2(), RpcProtocolV2.PROTOCOL_CODE);
    }
}
