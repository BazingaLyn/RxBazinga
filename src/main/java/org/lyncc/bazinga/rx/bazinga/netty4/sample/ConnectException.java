package org.lyncc.bazinga.rx.bazinga.netty4.sample;

/**
 * RPC连接异常
 *
 * @author biezhi
 *         2017/4/20
 */
public class ConnectException extends RuntimeException {

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

}
