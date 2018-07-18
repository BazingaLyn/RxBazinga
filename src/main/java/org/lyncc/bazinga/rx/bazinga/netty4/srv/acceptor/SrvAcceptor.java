package org.lyncc.bazinga.rx.bazinga.netty4.srv.acceptor;

import java.net.SocketAddress;

/**
 * 服务器抽象接口描述
 * @author liguolin
 * @create 2018-07-11 18:00
 **/
public interface SrvAcceptor {

    /**
     * 服务器地址
     * @return
     */
    SocketAddress localAddress();

    /**
     * 启动服务
     * @throws InterruptedException
     */
    void start() throws InterruptedException;

    /**
     * 平滑关闭服务器
     */
    void shutdownGracefully();

    /**
     * 是否异步启动
     * @param sync
     * @throws InterruptedException
     */
    void start(boolean sync) throws InterruptedException;
}
