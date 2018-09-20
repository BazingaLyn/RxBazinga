package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.apache.commons.lang3.StringUtils;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.GlobalSwitch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liguolin
 * @create 2018-07-23 17:44
 **/
public class RandomSelectStrategy implements ConnectionSelectStrategy {


    private static final Logger logger = LoggerFactory.getLogger(RandomSelectStrategy.class);

    /** max retry times */
    private static final int    MAX_TIMES = 5;

    /** random */
    private final Random random    = new Random();

    private GlobalSwitch globalSwitch;

    public RandomSelectStrategy() {
    }

    public RandomSelectStrategy(GlobalSwitch globalSwitch) {
        this.globalSwitch = globalSwitch;
    }


    @Override
    public Connection select(List<Connection> conns) {
        try {
            if (conns == null) {
                return null;
            }
            int size = conns.size();
            if (size == 0) {
                return null;
            }

            Connection result = null;
            if (null != this.globalSwitch
                    && this.globalSwitch.isOn(GlobalSwitch.CONN_MONITOR_SWITCH)) {
                List<Connection> serviceStatusOnConns = new ArrayList<Connection>();
                for (Connection conn : conns) {
                    String serviceStatus = (String) conn.getAttribute(Configs.CONN_SERVICE_STATUS);
                    if (!StringUtils.equals(serviceStatus, Configs.CONN_SERVICE_STATUS_OFF)) {
                        serviceStatusOnConns.add(conn);
                    }
                }
                if (serviceStatusOnConns.size() < 0) {
                    throw new Exception(
                            "No available connection when select in RandomSelectStrategy.");
                }
                result = randomGet(serviceStatusOnConns);
            } else {
                result = randomGet(conns);
            }
            return result;
        } catch (Throwable e) {
            logger.error("Choose connection failed using RandomSelectStrategy!", e);
            return null;
        }
    }

    /**
     * get one connection randomly
     *
     * @param conns
     * @return
     */
    private Connection randomGet(List<Connection> conns) {
        Connection result = null;
        int size = conns.size();
        int tries = 0;
        while ((result == null || !result.isFine()) && tries++ < MAX_TIMES) {
            result = conns.get(this.random.nextInt(size));
        }
        if (result != null && !result.isFine()) {
            result = null;
        }
        return result;
    }
}
