package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow;

import org.lyncc.bazinga.rx.bazinga.msentinel.config.SentinelConfig;
import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.util.StringUtil;

/**
 * @author liguolin
 * @create 2018-12-20 20:22
 **/
public class ColdFactorProperty {

    public static volatile int coldFactor = 3;

    static {

        String strConfig = SentinelConfig.getConfig(SentinelConfig.COLD_FACTOR);
        if (StringUtil.isBlank(strConfig)) {
            coldFactor = 3;
        } else {
            try {
                coldFactor = Integer.valueOf(strConfig);
            } catch (NumberFormatException e) {
                RecordLog.info(e.getMessage(), e);
            }

            if (coldFactor <= 1) {
                coldFactor = 3;
                RecordLog.info("cold factor should be larger than 3");
            }
        }
    }



}
