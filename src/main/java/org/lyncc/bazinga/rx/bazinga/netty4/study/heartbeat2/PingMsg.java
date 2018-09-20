package org.lyncc.bazinga.rx.bazinga.netty4.study.heartbeat2;

public class PingMsg extends BaseMsg{
    
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }

}
