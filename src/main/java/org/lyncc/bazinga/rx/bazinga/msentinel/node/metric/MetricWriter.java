package org.lyncc.bazinga.rx.bazinga.msentinel.node.metric;


import org.lyncc.bazinga.rx.bazinga.msentinel.config.SentinelConfig;
import org.lyncc.bazinga.rx.bazinga.msentinel.log.LogBase;
import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.util.PidUtil;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;


/**
 * @author liguolin
 * @create 2018-12-27 15:31
 **/
public class MetricWriter {

    private static final String CHARSET = SentinelConfig.charset();
    public static final String METRIC_BASE_DIR = RecordLog.getLogBaseDir();


    public static final String METRIC_FILE = "metrics.log";
    public static final String METRIC_FILE_INDEX_SUFFIX = ".idx";

    public static final Comparator<String> METRIC_FILE_NAME_CMP = new MetricFileNameComparator();
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private long timeSecondBase;
    private String baseDir;
    private String baseFileName;

    private File curMetricFile;
    private File curMetricIndexFile;


    private FileOutputStream outStream;
    private DataOutputStream outIndex;
    private BufferedOutputStream outMetricBuf;
    private long singleFileSize;
    private int totalFileCount;
    private boolean append = false;

    private final int pid = PidUtil.getPid();

    private long lastSecond = -1;


    public MetricWriter(long singleFileSize, int totalFileCount) {
        if (singleFileSize <= 0 || totalFileCount <= 0) {
            throw new IllegalArgumentException();
        }
        RecordLog.info(
                "[MetricWriter] Creating new MetricWriter, singleFileSize=" + singleFileSize + ", totalFileCount="
                        + totalFileCount);
        this.baseDir = METRIC_BASE_DIR;
        File dir = new File(baseDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        long time = System.currentTimeMillis();
        this.lastSecond = time / 1000;
        this.singleFileSize = singleFileSize;
        this.totalFileCount = totalFileCount;
        try {
            this.timeSecondBase = df.parse("1970-01-01 00:00:00").getTime() / 1000;
        } catch (Exception e) {
            RecordLog.warn("[MetricWriter] Create new MetricWriter error", e);
        }
    }

    public synchronized void write(long time,List<MetricNode> nodeList){
        if(nodeList == null){
            return;
        }

        for(MetricNode node : nodeList){
            node.setTimestamp(time);
        }

        String appName = SentinelConfig.getAppName();

        if(appName == null){
            appName = "";
        }

        if(curMetricFile == null){
            baseFileName = formMetricFileName(appName,pid);
        }

    }

    public static String formMetricFileName(String appName, int pid) {
        if (appName == null) {
            appName = "";
        }
        // dot is special char that should be replaced.
        final String dot = ".";
        final String separator = "-";
        if (appName.contains(dot)) {
            appName = appName.replace(dot, separator);
        }
        String name = appName + separator + METRIC_FILE;
        if (LogBase.isLogNameUsePid()) {
            name += ".pid" + pid;
        }
        return name;
    }

    private static final class MetricFileNameComparator implements Comparator<String> {
        private final String pid = "pid";

        @Override
        public int compare(String o1, String o2) {
            String name1 = new File(o1).getName();
            String name2 = new File(o2).getName();
            String dateStr1 = name1.split("\\.")[2];
            String dateStr2 = name2.split("\\.")[2];
            // in case of file name contains pid, skip it, like Sentinel-Admin-metrics.log.pid22568.2018-12-24
            if (dateStr1.startsWith(pid)) {
                dateStr1 = name1.split("\\.")[3];
                dateStr2 = name2.split("\\.")[3];
            }

            // compare date first
            int t = dateStr1.compareTo(dateStr2);
            if (t != 0) {
                return t;
            }

            // same date, compare file number
            t = name1.length() - name2.length();
            if (t != 0) {
                return t;
            }
            return name1.compareTo(name2);
        }
    }
}
