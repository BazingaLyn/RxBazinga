package org.lyncc.bazinga.rx.bazinga.netty4.study.heartbeat2;

public class ReplyMsg extends BaseMsg {
    
    public ReplyMsg() {
        super();
        setType(MsgType.REPLY);
    }
    
    private ReplyBody body;

    public ReplyBody getBody() {
        return body;
    }

    public void setBody(ReplyBody body) {
        this.body = body;
    }
    
    

}
