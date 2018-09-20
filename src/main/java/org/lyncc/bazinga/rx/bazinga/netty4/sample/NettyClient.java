package org.lyncc.bazinga.rx.bazinga.netty4.sample;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.lyncc.bazinga.rx.bazinga.netty4.sample.model.ServiceStatus;
import org.lyncc.bazinga.rx.bazinga.utils.HttpRequest;
import org.lyncc.bazinga.rx.bazinga.utils.StringUtils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * netty 客户端
 *
 * @author liguolin
 * @create 2018-01-17 11:22
 **/
public class NettyClient {

    private LongAdder retryCount = new LongAdder();

    private NettyConfig nettyConfig;

    private String address;

    private SocketAddress serverAddress;

    private int weight;

    /**
     * 并行处理器个数
     */
    private final static int            parallel       = Runtime.getRuntime().availableProcessors() + 1;
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(parallel);



    public NettyClient(NettyConfig nettyConfig, String address) {

        this.nettyConfig = nettyConfig;
        this.address = address;

        String host = address.split(":")[0];
        int    port = Integer.parseInt(address.split(":")[1]);
        this.serverAddress = new InetSocketAddress(host, port);
    }


    private Bootstrap createBootstrap(EventLoopGroup eventLoopGroup) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, nettyConfig.getConnTimeout())
                .option(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.handler(new HttpClientChannelInitializer(this));
        return bootstrap;
    }

    public Channel syncCreateChannel(EventLoopGroup eventLoopGroup) {
        if (LocalServiceNodeTable.isAlive(this.getAddress())) {
            return null;
        }

        Bootstrap bootstrap = this.createBootstrap(eventLoopGroup);
        // 和服务端建立连接,然后异步获取运行结果
        try {
            Channel channel = bootstrap.connect(serverAddress).sync().channel();
            while (!channel.isActive()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            //和服务器连接成功后, 获取MessageSendHandler对象
            Class<? extends SimpleClientHandler> clientHandler = HttpClientHandler.class;
            SimpleClientHandler handler       = channel.pipeline().get(clientHandler);

            // 设置节点状态为存活状态
            LocalServiceNodeTable.setNodeAlive(handler);

            String result = getServerStatus();
            if (StringUtils.isNotEmpty(result)) {
                ServiceStatus serviceStatus = JSON.parseObject(result, ServiceStatus.class);
                this.weight = serviceStatus.getWeight();
            }

//            this.enabledPing(channel);
            return channel;
        } catch (Exception e) {
            LocalServiceNodeTable.setNodeDead(address);
//            eventLoopGroup.shutdownGracefully();
            throw new ConnectException(String.format("Connect [%s] fail", address), e);
        }
    }

    private String getServerStatus() {
        return HttpRequest.get("http://" + this.getAddress() + "/status")
                .connectTimeout(10_000)
                .readTimeout(5000)
                .body();
    }





    public LongAdder getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(LongAdder retryCount) {
        this.retryCount = retryCount;
    }

    public NettyConfig getNettyConfig() {
        return nettyConfig;
    }

    public void setNettyConfig(NettyConfig nettyConfig) {
        this.nettyConfig = nettyConfig;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SocketAddress getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(SocketAddress serverAddress) {
        this.serverAddress = serverAddress;
    }
}
