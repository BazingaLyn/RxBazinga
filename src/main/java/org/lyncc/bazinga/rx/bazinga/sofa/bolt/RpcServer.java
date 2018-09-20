package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc.RpcAddressParser;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.GlobalSwitch;

/**
 * @author liguolin
 * @create 2018-07-18 15:37
 **/
public class RpcServer extends RemotingServer {


    private GlobalSwitch globalSwitch            = new GlobalSwitch();

    private RemotingAddressParser                       addressParser;


    public RpcServer(int port) {
        super(port);
    }

    public RpcServer(int port, boolean manageConnection) {
        this(port);
        /** server connection management feature enabled or not, default value false, means disabled. */
        if (manageConnection) {
            this.globalSwitch.turnOn(GlobalSwitch.SERVER_MANAGE_CONNECTION_SWITCH);
        }
    }

    @Override
    protected void doInit() {

        if (this.addressParser == null) {
            this.addressParser = new RpcAddressParser();
        }



    }

    @Override
    protected boolean doStart() {
        return false;
    }

    @Override
    protected void doStop() {

    }
}
