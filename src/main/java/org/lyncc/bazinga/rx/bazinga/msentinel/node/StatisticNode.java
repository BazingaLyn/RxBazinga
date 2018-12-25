package org.lyncc.bazinga.rx.bazinga.msentinel.node;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.metric.MetricNode;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticNode implements Node {


    private AtomicInteger curThreadNum = new AtomicInteger(0);

    @Override
    public Map<Long, MetricNode> metrics() {
        return null;
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

    }

    @Override
    public void increaseExceptionQps() {

    }

    @Override
    public void increaseThreadNum() {

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
