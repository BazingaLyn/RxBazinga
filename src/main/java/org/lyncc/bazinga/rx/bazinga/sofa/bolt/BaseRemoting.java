package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liguolin
 * @create 2018-07-22 9:51
 **/
public abstract class BaseRemoting {

    private static final Logger logger = LoggerFactory.getLogger(BaseRemoting.class);

    protected CommandFactory commandFactory;

    public BaseRemoting(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }



    protected RemotingCommand invokeSync(final Connection conn,final RemotingCommand request,final int timeoutMillis){

        final InvokeFuture future = createInvokeFuture(request, request.getInvokeContext());


        return null;
    }

    protected abstract InvokeFuture createInvokeFuture(RemotingCommand request, InvokeContext invokeContext);


    protected CommandFactory getCommandFactory() {
        return commandFactory;
    }


}
