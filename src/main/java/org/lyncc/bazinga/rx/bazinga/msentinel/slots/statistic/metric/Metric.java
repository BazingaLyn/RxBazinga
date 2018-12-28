package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.metric;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.metric.MetricNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.data.MetricBucket;

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


    MetricBucket[] window();

    /**
     * Increment by one the current exception count.
     */
    void addException();

    /**
     * Increment by one the current blovk count.
     */
    void addBlock();

    /**
     * Increment by one the current success count.
     */
    void addSuccess();

    /**
     * Increment by one the current pass count.
     */
    void addPass();

    /**
     * Add given RT to current total RT.
     *
     * @param rt RT
     */
    void addRT(long rt);

    double getWindowIntervalInSec();

    int getSampleCount();

    // Tool methods.

    void debugQps();



    long previousWindowBlock();



    long previousWindowPass();


}
