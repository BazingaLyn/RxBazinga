package org.lyncc.bazinga.rx.bazinga.msentinel.node.metric;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MetricNode {

    private long timestamp;
    private long passQps;
    private long blockQps;
    private long successQps;
    private long exceptionQps;
    private long rt;

    private String resource;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getPassQps() {
        return passQps;
    }

    public void setPassQps(long passQps) {
        this.passQps = passQps;
    }

    public long getBlockQps() {
        return blockQps;
    }

    public void setBlockQps(long blockQps) {
        this.blockQps = blockQps;
    }

    public long getSuccessQps() {
        return successQps;
    }

    public void setSuccessQps(long successQps) {
        this.successQps = successQps;
    }

    public long getExceptionQps() {
        return exceptionQps;
    }

    public void setExceptionQps(long exceptionQps) {
        this.exceptionQps = exceptionQps;
    }

    public long getRt() {
        return rt;
    }

    public void setRt(long rt) {
        this.rt = rt;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "MetricNode{" +
                "timestamp=" + timestamp +
                ", passQps=" + passQps +
                ", blockQps=" + blockQps +
                ", successQps=" + successQps +
                ", exceptionQps=" + exceptionQps +
                ", rt=" + rt +
                ", resource='" + resource + '\'' +
                '}';
    }

    public String toThinString() {
        StringBuilder sb = new StringBuilder();
        sb.append(timestamp).append("|");
        String legalName = resource.replaceAll("\\|", "_");
        sb.append(legalName).append("|");
        sb.append(passQps).append("|");
        sb.append(blockQps).append("|");
        sb.append(successQps).append("|");
        sb.append(exceptionQps).append("|");
        sb.append(rt);
        return sb.toString();
    }

    public static MetricNode fromThinString(String line) {
        MetricNode node = new MetricNode();
        String[] strs = line.split("\\|");
        node.setTimestamp(Long.parseLong(strs[0]));
        node.setResource(strs[1]);
        node.setPassQps(Long.parseLong(strs[2]));
        node.setBlockQps(Long.parseLong(strs[3]));
        node.setSuccessQps(Long.parseLong(strs[4]));
        node.setExceptionQps(Long.parseLong(strs[5]));
        node.setRt(Long.parseLong(strs[6]));
        return node;
    }

    public String toFatString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder(32);
        sb.delete(0, sb.length());
        sb.append(getTimestamp()).append("|");
        sb.append(df.format(new Date(getTimestamp()))).append("|");
        String legalName = getResource().replaceAll("\\|", "_");
        sb.append(legalName).append("|");
        sb.append(getPassQps()).append("|");
        sb.append(getBlockQps()).append("|");
        sb.append(getSuccessQps()).append("|");
        sb.append(getExceptionQps()).append("|");
        sb.append(getRt());
        sb.append('\n');
        return sb.toString();
    }

    public static MetricNode fromFatString(String line) {
        String[] strs = line.split("\\|");
        Long time = Long.parseLong(strs[0]);
        MetricNode node = new MetricNode();
        node.setTimestamp(time);
        node.setResource(strs[2]);
        node.setPassQps(Long.parseLong(strs[3]));
        node.setBlockQps(Long.parseLong(strs[4]));
        node.setSuccessQps(Long.parseLong(strs[5]));
        node.setExceptionQps(Long.parseLong(strs[6]));
        node.setRt(Long.parseLong(strs[7]));
        return node;
    }
}
