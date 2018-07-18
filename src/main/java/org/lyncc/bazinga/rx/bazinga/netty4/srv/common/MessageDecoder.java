package org.lyncc.bazinga.rx.bazinga.netty4.srv.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.lyncc.bazinga.rx.bazinga.netty4.srv.common.NettyCommonProtocol.*;
import static org.lyncc.bazinga.rx.bazinga.netty4.srv.serializer.SerializerHolder.serializerImpl;



