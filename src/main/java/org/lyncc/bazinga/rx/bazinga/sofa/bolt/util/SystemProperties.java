package org.lyncc.bazinga.rx.bazinga.sofa.bolt.util;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.Configs;

/**
 * @author liguolin
 * @create 2018-07-18 16:36
 **/
public class SystemProperties {

    public static boolean conn_reconnect_switch() {
        return getBool(Configs.CONN_RECONNECT_SWITCH, Configs.CONN_RECONNECT_SWITCH_DEFAULT);
    }

    public static boolean conn_monitor_switch() {
        return getBool(Configs.CONN_MONITOR_SWITCH, Configs.CONN_MONITOR_SWITCH_DEFAULT);
    }


    protected static boolean getBool(String key, String defaultValue) {
        return Boolean.parseBoolean(System.getProperty(key, defaultValue));
    }


}
