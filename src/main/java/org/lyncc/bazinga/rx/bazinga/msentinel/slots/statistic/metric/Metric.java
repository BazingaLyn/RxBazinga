package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.metric;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.metric.MetricNode;

import java.util.List;

/**
 * @author liguolin
 * @create 2018-12-25 18:55
 **/
public interface Metric {

    long success();


    long maxSuccess();


    long exception();


    long block();


    long pass();


    long rt();


    long minRt();


    List<MetricNode> details();






}
