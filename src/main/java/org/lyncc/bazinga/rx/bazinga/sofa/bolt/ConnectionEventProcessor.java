package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-30 14:43
 **/
public interface ConnectionEventProcessor {

    void onEvent(String remoteAddr, Connection conn);
}
