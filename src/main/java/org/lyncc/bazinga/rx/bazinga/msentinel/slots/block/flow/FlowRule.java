package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.AbstractRule;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.RuleConstant;

public class FlowRule extends AbstractRule {

    private int grade = RuleConstant.FLOW_GRADE_QPS;

    private double count;

    private int strategy = RuleConstant.STRATEGY_DIRECT;

    private String refResource;

    private int warmUpPeriodSec = 10;

    private int controlBehavior = RuleConstant.CONTROL_BEHAVIOR_DEFAULT;

    private int maxQueueingTimeMs = 500;

    private TrafficShapingController controller;

    public int getMaxQueueingTimeMs() {
        return maxQueueingTimeMs;
    }

    public void setMaxQueueingTimeMs(int maxQueueingTimeMs) {
        this.maxQueueingTimeMs = maxQueueingTimeMs;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public void setRefResource(String refResource) {
        this.refResource = refResource;
    }

    public int getWarmUpPeriodSec() {
        return warmUpPeriodSec;
    }

    public void setWarmUpPeriodSec(int warmUpPeriodSec) {
        this.warmUpPeriodSec = warmUpPeriodSec;
    }

    public void setControlBehavior(int controlBehavior) {
        this.controlBehavior = controlBehavior;
    }

    public void setController(TrafficShapingController controller) {
        this.controller = controller;
    }

    public int getGrade() {
        return grade;
    }

    public double getCount() {
        return count;
    }

    public int getStrategy() {
        return strategy;
    }

    public String getRefResource() {
        return refResource;
    }

    public int getControlBehavior() {
        return controlBehavior;
    }

    public TrafficShapingController getController() {
        return controller;
    }

    FlowRule setRater(TrafficShapingController rater){
        this.controller = rater;
        return this;
    }

    @Override
    public boolean passCheck(Context context, DefaultNode node, int count, Object... args) {
        return true;
    }
}
