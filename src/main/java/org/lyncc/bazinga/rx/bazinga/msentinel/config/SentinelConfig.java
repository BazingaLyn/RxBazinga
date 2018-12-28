package org.lyncc.bazinga.rx.bazinga.msentinel.config;



import org.lyncc.bazinga.rx.bazinga.msentinel.log.LogBase;
import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.util.AppNameUtil;
import org.lyncc.bazinga.rx.bazinga.msentinel.util.AssertUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liguolin
 * @create 2018-12-20 20:27
 **/
public class SentinelConfig {

    private static final Map<String,String> props = new ConcurrentHashMap<>();

    public static final String CHARSET = "csp.sentinel.charset";
    public static final String SINGLE_METRIC_FILE_SIZE = "csp.sentinel.metric.file.single.size";
    public static final String TOTAL_METRIC_FILE_COUNT = "csp.sentinel.metric.file.total.count";
    public static final String COLD_FACTOR = "csp.sentinel.flow.cold.factor";

    static final long DEFAULT_SINGLE_METRIC_FILE_SIZE = 1024 * 1024 * 50;
    static final int DEFAULT_TOTAL_METRIC_FILE_COUNT = 6;

    static{
        initialize();

    }

    private static void loadProps() {
        // Resolve app name.
        AppNameUtil.resolveAppName();
        try {
            String appName = AppNameUtil.getAppName();
            if (appName == null) {
                appName = "";
            }
            // We first retrieve the properties from the property file.
            String fileName = LogBase.getLogBaseDir() + appName + ".properties";
            File file = new File(fileName);
            if (file.exists()) {
                RecordLog.info("[SentinelConfig] Reading config from " + fileName);
                FileInputStream fis = new FileInputStream(fileName);
                Properties fileProps = new Properties();
                fileProps.load(fis);
                fis.close();

                for (Object key : fileProps.keySet()) {
                    SentinelConfig.setConfig((String)key, (String)fileProps.get(key));
                }
            }
        } catch (Throwable ioe) {
            RecordLog.info(ioe.getMessage(), ioe);
        }

        // JVM parameter override file config.
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            String configKey = entry.getKey().toString();
            String configValue = entry.getValue().toString();
            String configValueOld = getConfig(configKey);
            SentinelConfig.setConfig(configKey, configValue);
            if (configValueOld != null) {
                RecordLog.info("[SentinelConfig] JVM parameter overrides {0}: {1} -> {2}", configKey, configValueOld, configValue);
            }
        }
    }


    private static void initialize(){
        SentinelConfig.setConfig(CHARSET,"utf-8");
        SentinelConfig.setConfig(SINGLE_METRIC_FILE_SIZE,String.valueOf(DEFAULT_SINGLE_METRIC_FILE_SIZE));
        SentinelConfig.setConfig(TOTAL_METRIC_FILE_COUNT,String.valueOf(DEFAULT_TOTAL_METRIC_FILE_COUNT));
        SentinelConfig.setConfig(COLD_FACTOR, String.valueOf(3));
    }

    public static String charset() {
        return props.get(CHARSET);
    }

    public static void setConfig(String key,String value){
        AssertUtil.notNull(key, "key cannot be null");
        AssertUtil.notNull(value, "value cannot be null");
        props.put(key, value);
    }


    public static String getConfig(String key) {
        return props.get(key);
    }

    public static String getAppName() {
        return AppNameUtil.getAppName();
    }
}
