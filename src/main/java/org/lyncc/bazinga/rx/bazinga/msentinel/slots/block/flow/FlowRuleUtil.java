package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow;

import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.RuleConstant;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.controller.DefaultController;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.controller.RateLimiterController;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.controller.WarmUpController;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.controller.WarmUpRateLimiterController;
import org.lyncc.bazinga.rx.bazinga.msentinel.util.StringUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class FlowRuleUtil {


    private static final Function<FlowRule,String> extractResource = flowRule -> null;

    private FlowRuleUtil(){

    }

    public static Map<String, List<FlowRule>> buildFlowRuleMap(List<FlowRule> list){
        return buildFlowRuleMap(list,null);
    }

    private static Map<String, List<FlowRule>> buildFlowRuleMap(List<FlowRule> list, Predicate<FlowRule> filter) {
        return buildFlowRuleMap(list,filter,true);
    }

    private static Map<String, List<FlowRule>> buildFlowRuleMap(List<FlowRule> list, Predicate<FlowRule> filter, boolean shouldSort) {
        return buildFlowRuleMap(list, extractResource, filter, shouldSort);
    }

    private static Map<String, List<FlowRule>> buildFlowRuleMap(List<FlowRule> list,
                                                                Function<FlowRule, String> extractResource,
                                                                Predicate<FlowRule> filter,
                                                                boolean shouldSort) {

        Map<String,List<FlowRule>> newRuleMap = new ConcurrentHashMap<>();
        if(list == null || list.isEmpty()){
            return newRuleMap;
        }

        for(FlowRule rule :list){
            if(!isValidRule(rule)){
                RecordLog.warn("[FlowRuleManager] Ignoring invalid flow rule when loading new flow rules: " + rule);
                continue;
            }

            if(filter != null && filter.test(rule)){
                continue;
            }

            if(StringUtil.isBlank(rule.getLimitApp())){
                rule.setLimitApp(RuleConstant.LIMIT_APP_DEFAULT);
            }

            TrafficShapingController rater = generateRater(rule);
            rule.setRater(rater);

            String key = extractResource.apply(rule);
            if(key == null){
                continue;
            }

            List<FlowRule> flowRules = newRuleMap.get(key);

            if(flowRules == null){
                flowRules = new ArrayList<>();
                newRuleMap.put(key,flowRules);
            }

            flowRules.add(rule);
        }

        if(shouldSort && !newRuleMap.isEmpty()){
            Comparator<FlowRule> comparator = new FlowRuleComparator();

            // Sort the rules.
            for (List<FlowRule> rules : newRuleMap.values()) {
                Collections.sort(rules, comparator);
            }
        }
        return newRuleMap;
    }

    private static TrafficShapingController generateRater(FlowRule rule) {

        if(rule.getGrade() == RuleConstant.FLOW_GRADE_QPS){
            switch (rule.getControlBehavior()) {
                case RuleConstant.CONTROL_BEHAVIOR_WARM_UP:
                    return new WarmUpController(rule.getCount(),
                                                rule.getWarmUpPeriodSec(),
                                                ColdFactorProperty.coldFactor);
                case RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER:
                    return new RateLimiterController(rule.getMaxQueueingTimeMs(), rule.getCount());
                case RuleConstant.CONTROL_BEHAVIOR_WARM_UP_RATE_LIMITER:
                    return new WarmUpRateLimiterController(rule.getCount(), rule.getWarmUpPeriodSec(),
                            rule.getMaxQueueingTimeMs(), ColdFactorProperty.coldFactor);
                case RuleConstant.CONTROL_BEHAVIOR_DEFAULT:

            }
        }

        return new DefaultController(rule.getCount(), rule.getGrade());
    }

    //TODO
    private static boolean isValidRule(FlowRule rule) {
        return true;
    }
}
