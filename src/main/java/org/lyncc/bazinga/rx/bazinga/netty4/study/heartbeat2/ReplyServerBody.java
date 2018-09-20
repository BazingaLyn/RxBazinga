package org.lyncc.bazinga.rx.bazinga.netty4.study.heartbeat2;

public class ReplyServerBody extends ReplyBody {
    
    private String serverInfo;
    
    public ReplyServerBody(String serverInfo) {
        this.serverInfo = serverInfo;
    }
    public String getServerInfo() {
        return serverInfo;
    }
    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
}