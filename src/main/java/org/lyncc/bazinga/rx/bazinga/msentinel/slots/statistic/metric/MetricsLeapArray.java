package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.metric;

import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.base.LeapArray;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.data.MetricBucket;

/**
 * @author liguolin
 * @create 2018-12-25 19:34
 **/
public class MetricsLeapArray extends LeapArray<MetricBucket> {


    public MetricsLeapArray(int sampleCount,int intervalInMs){
        super(sampleCount,intervalInMs);
    }
}
