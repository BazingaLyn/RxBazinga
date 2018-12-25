package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.data;

import org.lyncc.bazinga.rx.bazinga.msentinel.Constants;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.MetricEvent;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author liguolin
 * @create 2018-12-25 19:06
 **/
public class MetricBucket {

    private final LongAdder[] counters;

    private volatile long minRt;

    public MetricBucket(){
        MetricEvent[] events = MetricEvent.values();
        this.counters = new LongAdder[events.length];
        for(MetricEvent event :events){
            counters[event.ordinal()] = new LongAdder();
        }
        initMinRt();
    }

    private void initMinRt() {
        this.minRt = Constants.TIME_DROP_VALVE;
    }

    public MetricBucket reset(){
        for(MetricEvent event : MetricEvent.values()){
            counters[event.ordinal()].reset();
        }
        initMinRt();
        return this;
    }

    public long get(MetricEvent event){
        return counters[event.ordinal()].sum();
    }

    public MetricBucket add(MetricEvent event,long n){
        counters[event.ordinal()].add(n);
        return this;
    }

    public long pass() {
        return get(MetricEvent.PASS);
    }

    public long block(){
        return get(MetricEvent.BLOCK);
    }

    public long exception(){
        return get(MetricEvent.EXCEPTION);
    }

    public long rt(){
        return get(MetricEvent.RT);
    }

    public long minRt(){
        return minRt;
    }

    public long success(){
        return get(MetricEvent.SUCCESS);
    }

    public void addPass(){
        add(MetricEvent.PASS,1);
    }

    public void addException(){
        add(MetricEvent.EXCEPTION,1);
    }

    public void addBlock(){
        add(MetricEvent.BLOCK,1);
    }

    public void addSuccess(){
        add(MetricEvent.SUCCESS,1);
    }


    public void addRT(long rt){
        add(MetricEvent.RT,rt);

        if(rt < minRt){
            minRt = rt;
        }
    }

}
