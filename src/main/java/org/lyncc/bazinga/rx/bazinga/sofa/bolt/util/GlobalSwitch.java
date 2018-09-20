package org.lyncc.bazinga.rx.bazinga.sofa.bolt.util;


import java.util.BitSet;

/**
 * @author liguolin
 * @create 2018-07-18 15:48
 **/
public class GlobalSwitch implements Switch {


    // switches
    public static final int CONN_RECONNECT_SWITCH           = 0;
    public static final int CONN_MONITOR_SWITCH             = 1;
    public static final int SERVER_MANAGE_CONNECTION_SWITCH = 2;
    public static final int SERVER_SYNC_STOP                = 3;

    /** system settings */
    private static BitSet systemSettings                  = new BitSet();

    /** user settings */
    private BitSet          userSettings                    = new BitSet();

    /**
     * init system switch status according to system properties
     */
    static {
        init();
    }


    private static void init() {
        if (SystemProperties.conn_reconnect_switch()) {
            systemSettings.set(CONN_RECONNECT_SWITCH);
        } else {
            systemSettings.clear(CONN_RECONNECT_SWITCH);
        }

        if (SystemProperties.conn_monitor_switch()) {
            systemSettings.set(CONN_MONITOR_SWITCH);
        } else {
            systemSettings.clear(CONN_MONITOR_SWITCH);
        }
    }


    @Override
    public void turnOn(int switchIndex) {
        this.userSettings.set(switchIndex);
    }

    @Override
    public boolean isOn(int switchIndex) {
        return systemSettings.get(switchIndex) || this.userSettings.get(switchIndex);
    }

    public static void reinit() {
        init();
    }
}
