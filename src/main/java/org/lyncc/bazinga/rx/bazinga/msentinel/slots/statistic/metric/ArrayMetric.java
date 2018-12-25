package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.metric;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.metric.MetricNode;

import java.util.List;

/**
 * @author liguolin
 * @create 2018-12-25 19:25
 **/
public class ArrayMetric implements Metric {


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
        return null;
    }
}
