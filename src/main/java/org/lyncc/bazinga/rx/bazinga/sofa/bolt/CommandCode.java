package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-21 21:06
 **/
public interface CommandCode {

    // value 0 is occupied by heartbeat, don't use value 0 for other commands
    short HEARTBEAT_VALUE = 0;

    /**
     *
     * @return the short value of the code
     */
    short value();
}
