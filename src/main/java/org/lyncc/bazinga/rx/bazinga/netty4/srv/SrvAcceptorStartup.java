package org.lyncc.bazinga.rx.bazinga.netty4.srv;

import org.lyncc.bazinga.rx.bazinga.netty4.srv.acceptor.DefaultCommonSrvAcceptor;

/**
 * @author liguolin
 * @create 2018-07-11 17:16
 **/
public class SrvAcceptorStartup {

    public static void main(String[] args) throws InterruptedException {

        DefaultCommonSrvAcceptor defaultCommonSrvAcceptor = new DefaultCommonSrvAcceptor(20011,null);
        defaultCommonSrvAcceptor.start();
    }
}
