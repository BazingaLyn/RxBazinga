package org.lyncc.bazinga.rx.bazinga.msentinel.node;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.metric.MetricNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.metric.ArrayMetric;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.metric.Metric;
import org.lyncc.bazinga.rx.bazinga.msentinel.util.TimeUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticNode implements Node {


    private transient volatile Metric rollingCounterInSecond = new ArrayMetric(SampleCountProperty.SAMPLE_COUNT,
            IntervalProperty.INTERVAL);

    private transient volatile Metric rollingCounterInMinute = new ArrayMetric(60,60 * 1000);

    private AtomicInteger curThreadNum = new AtomicInteger(0);

    private long lastFetchTime = -1;

    @Override
    public Map<Long, MetricNode> metrics() {

        long currentTime = TimeUtil.currentTimeMillis();
        currentTime = currentTime - currentTime % 1000;

        Map<Long, MetricNode> metricNodeMap = new ConcurrentHashMap<>();
        //
        List<MetricNode> details = rollingCounterInMinute.details();

        long newLastFetchTime = lastFetchTime;
        for(MetricNode node : details){
            //其实只是统计当前秒的信息
            if(isNodeInTime(node,currentTime) && isValidMetricNode(node)){
                metricNodeMap.put(node.getTimestamp(),node);
                newLastFetchTime = Math.max(newLastFetchTime,node.getTimestamp());
            }
        }
        lastFetchTime = newLastFetchTime;
        return metricNodeMap;
    }

    private boolean isValidMetricNode(MetricNode node) {
        return node.getPassQps() > 0 || node.getBlockQps() > 0 || node.getSuccessQps() > 0
                || node.getExceptionQps() > 0 || node.getRt() > 0;
    }

    private boolean isNodeInTime(MetricNode node, long currentTime) {
        return node.getTimestamp() > lastFetchTime && node.getTimestamp() < currentTime;
    }


    @Override
    public long totalRequest() {
        return 0;
    }

    @Override
    public long totalSuccess() {
        return 0;
    }

    @Override
    public long blockRequest() {
        return 0;
    }

    @Override
    public long totalException() {
        return 0;
    }

    @Override
    public long passQps() {
        return 0;
    }

    @Override
    public long blockQps() {
        return 0;
    }

    @Override
    public long totalQps() {
        return 0;
    }

    @Override
    public long successQps() {
        return 0;
    }

    @Override
    public long maxSuccessQps() {
        return 0;
    }

    @Override
    public long exceptionQps() {
        return 0;
    }

    @Override
    public long avgRt() {
        return 0;
    }

    @Override
    public long minRt() {
        return 0;
    }

    @Override
    public int curThreadNum() {
        return 0;
    }

    @Override
    public long previousBlockQps() {
        return 0;
    }

    @Override
    public long previousPassQps() {
        return 0;
    }

    @Override
    public void addPassRequest() {

    }

    @Override
    public void rt(long rt) {

    }

    @Override
    public void increaseBlockQps() {
        rollingCounterInMinute.addBlock();
        rollingCounterInSecond.addBlock();
    }

    @Override
    public void increaseExceptionQps() {

    }

    @Override
    public void increaseThreadNum() {
        curThreadNum.incrementAndGet();
    }

    @Override
    public void decreaseThreadNum() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void debug() {

    }
}
