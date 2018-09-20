package org.lyncc.bazinga.rx.bazinga.sofa.bolt.util;

/**
 * @author liguolin
 * @create 2018-07-18 15:46
 **/
public interface Switch {

    /**
     * api for user to turn on a feature
     *
     * @param index the switch index of feature
     */
    void turnOn(int index);

    /**
     * check switch whether on
     *
     * @param index
     * @return true if either system setting is on or user setting is on
     */
    boolean isOn(int index);
}
