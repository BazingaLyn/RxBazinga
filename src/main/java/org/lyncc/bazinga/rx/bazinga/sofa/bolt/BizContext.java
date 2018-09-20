package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-18 17:04
 **/
public interface BizContext {

    /**
     * get remote address
     *
     * @return
     */
    String getRemoteAddress();

    /**
     * get remote host ip
     *
     * @return
     */
    String getRemoteHost();

    /**
     * get remote port
     *
     * @return
     */
    int getRemotePort();

    /**
     * get the connection of this request
     *
     * @return
     */
    Connection getConnection();

    /**
     * check whether request already timeout
     *
     * @return true if already timeout, you can log some useful info and then discard this request.
     */
    boolean isRequestTimeout();

    /**
     * get the timeout value from rpc client.
     *
     * @return
     */
    int getClientTimeout();

    /**
     * get the arrive time stamp
     *
     * @return
     */
    long getArriveTimestamp();

    /**
     * put a key and value
     *
     * @return
     */
    void put(String key, String value);

    /**
     * get value
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * get invoke context.
     *
     * @return
     */
    InvokeContext getInvokeContext();
}
