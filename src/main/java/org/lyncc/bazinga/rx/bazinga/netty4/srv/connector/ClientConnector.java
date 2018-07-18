package org.lyncc.bazinga.rx.bazinga.netty4.srv.connector;

import io.netty.channel.Channel;

public interface ClientConnector {

    Channel connect(int port, String host);

    void shutdownGracefully();

}
