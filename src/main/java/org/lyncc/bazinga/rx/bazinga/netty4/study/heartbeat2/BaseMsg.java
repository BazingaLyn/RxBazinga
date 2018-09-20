package org.lyncc.bazinga.rx.bazinga.netty4.study.heartbeat2;

import java.io.Serializable;

public abstract class BaseMsg implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private MsgType type;
    
    private String clientId;
    
    public BaseMsg() {
        this.clientId = Constants.getClientId();
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    

}
