package org.lyncc.bazinga.rx.bazinga.metric;

import com.alibaba.csp.sentinel.node.metric.MetricNode;
import com.alibaba.csp.sentinel.slots.statistic.data.MetricBucket;

import java.util.List;

public interface Metric {

    long success();


    long maxSuccess();


    long exception();


    long block();


    long pass();


    long rt();

    long minRt();

    List<MetricNode> details();

    MetricBucket[] windows();

    void addException(int n);

    void addBlock(int n);

    void addSuccess(int n);

    void addPass(int n);

    void addRT(long rt);

    double getWindowIntervalInSec();

    int getSampleCount();

    long getWindowPass(long timeMillis);





}
