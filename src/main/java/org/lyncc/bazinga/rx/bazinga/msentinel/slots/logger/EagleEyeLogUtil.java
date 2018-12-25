package org.lyncc.bazinga.rx.bazinga.msentinel.slots.logger;

import com.alibaba.csp.sentinel.eagleeye.EagleEye;
import com.alibaba.csp.sentinel.eagleeye.StatLogger;
import com.alibaba.csp.sentinel.log.LogBase;

public class EagleEyeLogUtil {

    public static final String FILE_NAME = "sentinel-block.log";

    private static StatLogger statLogger;

    static {
        String path = LogBase.getLogBaseDir() + FILE_NAME;

        statLogger = EagleEye.statLoggerBuilder("sentinel-block-log")
            .intervalSeconds(1)
            .entryDelimiter('|')
            .keyDelimiter(',')
            .valueDelimiter(',')
            .maxEntryCount(6000)
            .configLogFilePath(path)
            .maxFileSizeMB(300)
            .maxBackupIndex(3)
            .buildSingleton();
    }

    public static void log(String resource, String exceptionName, String ruleLimitApp, String origin, int count) {
        statLogger.stat(resource, exceptionName, ruleLimitApp, origin).count(count);
    }
}
