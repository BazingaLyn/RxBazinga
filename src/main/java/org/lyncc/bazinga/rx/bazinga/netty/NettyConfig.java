package org.lyncc.bazinga.rx.bazinga.netty;

/**
 * netty基本配置
 *
 * @author liguolin
 * @create 2018-01-16 15:53
 **/
public class NettyConfig {

    private int connTimeout = 3000;

    private int backlog;

    private boolean keepalive;

    private int lowWaterMark           = 32 * 1024;
    private int highWaterMark          = 64 * 1024;


    private int businessThreadPoolSize = Runtime.getRuntime().availableProcessors() * 2;

    public int getConnTimeout() {
        return connTimeout;
    }

    public void setConnTimeout(int connTimeout) {
        this.connTimeout = connTimeout;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    public boolean isKeepalive() {
        return keepalive;
    }

    public void setKeepalive(boolean keepalive) {
        this.keepalive = keepalive;
    }

    public int getLowWaterMark() {
        return lowWaterMark;
    }

    public void setLowWaterMark(int lowWaterMark) {
        this.lowWaterMark = lowWaterMark;
    }

    public int getHighWaterMark() {
        return highWaterMark;
    }

    public void setHighWaterMark(int highWaterMark) {
        this.highWaterMark = highWaterMark;
    }

    public int getBusinessThreadPoolSize() {
        return businessThreadPoolSize;
    }

    public void setBusinessThreadPoolSize(int businessThreadPoolSize) {
        this.businessThreadPoolSize = businessThreadPoolSize;
    }
}
