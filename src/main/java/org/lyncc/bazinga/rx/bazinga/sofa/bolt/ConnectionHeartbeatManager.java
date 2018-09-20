package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-27 13:38
 **/
public interface ConnectionHeartbeatManager {


    void disableHeartbeat(Connection connection);



    void enableHeartbeat(Connection connection);
}
