package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-18 16:38
 **/
public class Configs {


    public static final String CONN_RECONNECT_SWITCH =  "bolt.conn.reconnect.switch";


    public static final String CONN_RECONNECT_SWITCH_DEFAULT         = "false";


    public static final String CONN_MONITOR_SWITCH                   = "bolt.conn.monitor.switch";
    public static final String CONN_MONITOR_SWITCH_DEFAULT           = "false";

    /** default connection number per url */
    public static final int    DEFAULT_CONN_NUM_PER_URL              = 1;

    /** max connection number of each url */
    public static final int    MAX_CONN_NUM_PER_URL                  = 100 * 10000;


    /** Default connect timeout value, time unit: ms  */
    public static final int    DEFAULT_CONNECT_TIMEOUT               = 1000;

    /** Connection status */
    public static final String CONN_SERVICE_STATUS                   = "bolt.conn.service.status";
    public static final String CONN_SERVICE_STATUS_OFF               = "off";
    public static final String CONN_SERVICE_STATUS_ON                = "on";
}
