package org.lyncc.bazinga.rx.bazinga.netty4.study.heartbeat2;

public class ReplyClientBody extends ReplyBody {
    
    private String clientInfo;

    public ReplyClientBody(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }
}
