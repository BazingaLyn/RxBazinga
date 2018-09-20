package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import java.util.List;

/**
 * @author liguolin
 * @create 2018-07-23 17:39
 **/
public interface ConnectionSelectStrategy {

    /**
     * select strategy
     *
     * @param conns
     * @return
     */
    public Connection select(List<Connection> conns);
}
