package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liguolin
 * @create 2018-07-18 16:58
 **/
public class RemotingContext {


    private ChannelHandlerContext channelContext;

    private boolean                                     serverSide     = false;

    /** whether need handle request timeout, if true, request will be discarded. The default value is true */
    private boolean                                     timeoutDiscard = true;

    /** request arrive time stamp */
    private long                                        arriveTimestamp;

    /** request timeout setting by invoke side */
    private int                                         timeout;

    /** rpc command type */
    private int                                         rpcCommandType;

    private ConcurrentHashMap<String, UserProcessor<?>> userProcessors;

    private InvokeContext                               invokeContext;


}
