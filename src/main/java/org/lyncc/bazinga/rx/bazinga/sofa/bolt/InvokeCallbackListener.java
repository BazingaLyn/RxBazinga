package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-22 10:19
 **/
public interface InvokeCallbackListener {

    /**
     * Response arrived.
     *
     * @param future
     */
    public void onResponse(final InvokeFuture future);

    /**
     * Get the remote address.
     *
     * @return
     */
    public String getRemoteAddress();
}
