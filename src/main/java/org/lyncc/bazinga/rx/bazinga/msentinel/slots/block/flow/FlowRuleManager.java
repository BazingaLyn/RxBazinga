package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow;

import org.lyncc.bazinga.rx.bazinga.msentinel.concurrent.NamedThreadFactory;
import org.lyncc.bazinga.rx.bazinga.msentinel.property.DynamicSentinelProperty;
import org.lyncc.bazinga.rx.bazinga.msentinel.property.PropertyListener;
import org.lyncc.bazinga.rx.bazinga.msentinel.property.SentinelProperty;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class FlowRuleManager {

    private static final Map<String, List<FlowRule>> flowRules = new ConcurrentHashMap<>();

    private static final FlowPropertyListener LISTENER = new FlowPropertyListener();

    private static SentinelProperty<List<FlowRule>> currentProperty = new DynamicSentinelProperty<>();

    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1,
            new NamedThreadFactory("sentinel-metrics-record-task", true));


    static {
        //TODO
        currentProperty.addListener(LISTENER);
//        SCHEDULER.scheduleAtFixedRate(new Metric);
    }


    public static void loadRules(List<FlowRule> rules){
        currentProperty.updateValue(rules);
    }


    static Map<String,List<FlowRule>> getFlowRuleMap(){
        return flowRules;
    }


    private static final class FlowPropertyListener implements PropertyListener<List<FlowRule>> {

        @Override
        public void configUpdate(List<FlowRule> value) {

            Map<String,List<FlowRule>> rules = FlowRuleUtil.buildFlowRuleMap(value);
        }

        @Override
        public void configLoad(List<FlowRule> value) {

        }
    }
}
