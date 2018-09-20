package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import io.netty.util.Timeout;

import java.net.InetSocketAddress;

/**
 * @author liguolin
 * @create 2018-07-22 10:01
 **/
public interface InvokeFuture {

    RemotingCommand waitResponse(final long timeoutMillis) throws InterruptedException;


    RemotingCommand waitResponse() throws InterruptedException;


    RemotingCommand createConnectionClosedResponse(InetSocketAddress responseHost);


    void putResponse(final RemotingCommand response);


    int invokeId();


    void executeInvokeCallback();


    void tryAsyncExecuteInvokeCallbackAbnormally();


    void setCause(Throwable cause);


    Throwable getCause();


    InvokeCallback getInvokeCallback();



    void addTimeout(Timeout timeout);



    void cancelTimeout();



    boolean isDone();



    ClassLoader getAppClassLoader();



    byte getProtocolCode();



    void setInvokeContext(InvokeContext invokeContext);




    InvokeContext getInvokeContext();



}
