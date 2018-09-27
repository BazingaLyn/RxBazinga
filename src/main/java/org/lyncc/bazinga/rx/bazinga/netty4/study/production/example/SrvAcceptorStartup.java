package org.lyncc.bazinga.rx.bazinga.netty4.study.production.example;


import org.lyncc.bazinga.rx.bazinga.netty4.study.production.srv.acceptor.DefaultCommonSrvAcceptor;

public class SrvAcceptorStartup {
	
	public static void main(String[] args) throws InterruptedException {
		
		DefaultCommonSrvAcceptor defaultCommonSrvAcceptor = new DefaultCommonSrvAcceptor(20011,null);
		defaultCommonSrvAcceptor.start();
		
	}

}
