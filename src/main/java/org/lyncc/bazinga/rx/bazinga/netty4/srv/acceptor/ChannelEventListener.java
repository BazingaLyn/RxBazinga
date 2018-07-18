package org.lyncc.bazinga.rx.bazinga.netty4.srv.acceptor;

import io.netty.channel.Channel;

/**
 * @author liguolin
 * @create 2018-07-16 9:52
 **/
public interface ChannelEventListener {

    void onChannelConnect(final String remoteAddr, final Channel channel);


    void onChannelClose(final String remoteAddr, final Channel channel);


    void onChannelException(final String remoteAddr, final Channel channel);


    void onChannelIdle(final String remoteAddr, final Channel channel);
}
