package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.metric;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.metric.MetricNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.base.WindowWrap;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.data.MetricBucket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liguolin
 * @create 2018-12-25 19:25
 **/
public class ArrayMetric implements Metric {

    private final MetricsLeapArray data;

    public ArrayMetric(int sampleCount,int intervalInMs){
        this.data = new MetricsLeapArray(sampleCount,intervalInMs);
    }


    @Override
    public long success() {
        return 0;
    }

    @Override
    public long maxSuccess() {
        return 0;
    }

    @Override
    public long exception() {
        return 0;
    }

    @Override
    public long block() {
        return 0;
    }

    @Override
    public long pass() {
        return 0;
    }

    @Override
    public long rt() {
        return 0;
    }

    @Override
    public long minRt() {
        return 0;
    }

    @Override
    public List<MetricNode> details() {

        List<MetricNode> metricNodes = new ArrayList<>();
        // 更新windows
        data.currentWindow();

        for(WindowWrap<MetricBucket> windowWrap : data.list()){
            if(windowWrap == null){
                continue;
            }
            MetricNode node = new MetricNode();
            node.setBlockQps(windowWrap.value().block());
            node.setExceptionQps(windowWrap.value().exception());
            // 通过的QPS
            node.setPassQps(windowWrap.value().pass());
            // 通过的QPS + 处理成功的
            long successPassQps = windowWrap.value().success();
            node.setSuccessQps(successPassQps);

            if(successPassQps != 0){
                node.setRt(windowWrap.value().rt() / successPassQps);
            }else{
                node.setRt(windowWrap.value().rt());
            }
            node.setTimestamp(windowWrap.windowStart());

            metricNodes.add(node);
        }
        return metricNodes;
    }

    @Override
    public MetricBucket[] window() {
        data.currentWindow();
        return data.values().toArray(new MetricBucket[data.values().size()]);
    }

    /**
     * Increment by one the current exception count.
     */
    @Override
    public void addException() {
        WindowWrap<MetricBucket> wrap = data.currentWindow();
        wrap.value().addException();
    }

    /**
     * Increment by one the current blovk count.
     */
    @Override
    public void addBlock() {
        WindowWrap<MetricBucket> wrap = data.currentWindow();
        wrap.value().addBlock();
    }

    /**
     * Increment by one the current success count.
     */
    @Override
    public void addSuccess() {
        WindowWrap<MetricBucket> wrap = data.currentWindow();
        wrap.value().addSuccess();
    }

    /**
     * Increment by one the current pass count.
     */
    @Override
    public void addPass() {

    }

    /**
     * Add given RT to current total RT.
     *
     * @param rt RT
     */
    @Override
    public void addRT(long rt) {

    }

    @Override
    public double getWindowIntervalInSec() {
        return 0;
    }

    @Override
    public int getSampleCount() {
        return 0;
    }

    @Override
    public void debugQps() {

    }

    @Override
    public long previousWindowBlock() {
        return 0;
    }

    @Override
    public long previousWindowPass() {
        return 0;
    }

}
