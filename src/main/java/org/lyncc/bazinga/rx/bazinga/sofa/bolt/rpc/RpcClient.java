/*
package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.*;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.GlobalSwitch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

*/
/**
 * @author liguolin
 * @create 2018-07-21 21:49
 **//*

public class RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    */
/** global switch *//*

    private GlobalSwitch              globalSwitch             = new GlobalSwitch();


    protected RpcRemoting rpcRemoting;

    private RemotingAddressParser addressParser;


    private ConnectionSelectStrategy connectionSelectStrategy = new RandomSelectStrategy(
            globalSwitch);


    private DefaultConnectionManager  connectionManager        = new DefaultConnectionManager(
            connectionSelectStrategy,
            connctionFactory,
            connectionEventHandler,
            connectionEventListener,
            globalSwitch);




    public void init(){
        if (this.addressParser == null) {
            this.addressParser = new RpcAddressParser();
        }
    }
}
*/
