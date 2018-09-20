package org.lyncc.bazinga.rx.bazinga.netty4.study.codec.custom;

public class ProtocolMsg {
    
    private ProtocolHeader protocolHeader = new ProtocolHeader();
    
    private String body;
    
    public ProtocolMsg() {
        
    }

    public ProtocolHeader getProtocolHeader() {
        return protocolHeader;
    }

    public void setProtocolHeader(ProtocolHeader protocolHeader) {
        this.protocolHeader = protocolHeader;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
