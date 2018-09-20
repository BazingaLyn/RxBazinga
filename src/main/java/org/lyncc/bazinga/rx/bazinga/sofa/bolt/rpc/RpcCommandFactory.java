package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.CommandFactory;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.RemotingCommand;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.ResponseStatus;

import java.net.InetSocketAddress;

/**
 * @author liguolin
 * @create 2018-07-21 21:42
 **/
public class RpcCommandFactory implements CommandFactory {


    @Override
    public <T extends RemotingCommand> T createRequestCommand(Object requestObject) {
        return null;
    }

    @Override
    public <T extends RemotingCommand> T createResponse(Object responseObject, RemotingCommand requestCmd) {
        return null;
    }

    @Override
    public <T extends RemotingCommand> T createExceptionResponse(int id, String errMsg) {
        return null;
    }

    @Override
    public <T extends RemotingCommand> T createExceptionResponse(int id, Throwable t, String errMsg) {
        return null;
    }

    @Override
    public <T extends RemotingCommand> T createExceptionResponse(int id, ResponseStatus status) {
        return null;
    }

    @Override
    public <T extends RemotingCommand> T createTimeoutResponse(InetSocketAddress address) {
        return null;
    }

    @Override
    public <T extends RemotingCommand> T createSendFailedResponse(InetSocketAddress address, Throwable throwable) {
        return null;
    }

    @Override
    public <T extends RemotingCommand> T createConnectionClosedResponse(InetSocketAddress address, String message) {
        return null;
    }
}
