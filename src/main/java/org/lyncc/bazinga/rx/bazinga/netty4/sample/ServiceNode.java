package org.lyncc.bazinga.rx.bazinga.netty4.sample;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 服务节点
 *
 * @author liguolin
 * @create 2018-01-17 14:24
 **/
public class ServiceNode {

    /**
     * 服务地址，存储在注册中心都地址
     */
    private String              serverAddress;
    /**
     * 客户端连接
     */
    private SimpleClientHandler clientHandler;
    /**
     * 节点存活状态
     */
    private NodeAliveStateEnum  aliveState;
    /**
     * 是否开始尝试连接
     */
    private boolean             connected;
    /**
     * 该节点下都服务列表
     */
    private Set<String> services = Sets.newHashSet();

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public SimpleClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(SimpleClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public NodeAliveStateEnum getAliveState() {
        return aliveState;
    }

    public void setAliveState(NodeAliveStateEnum aliveState) {
        this.aliveState = aliveState;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }

    public enum NodeAliveStateEnum {

        ALIVE("存活"),
        DEAD("挂掉"),
        CONNECTING("连接中");

        private final String state;

        NodeAliveStateEnum(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}
