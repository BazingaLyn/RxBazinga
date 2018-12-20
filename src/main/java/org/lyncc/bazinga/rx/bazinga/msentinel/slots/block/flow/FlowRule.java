package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.AbstractRule;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.RuleConstant;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.controller.TrafficShapingController;

public class FlowRule extends AbstractRule {



    private int grade = RuleConstant.FLOW_GRADE_QPS;

    private double count;

    private int strategy = RuleConstant.STRATEGY_DIRECT;

    private String refResource;

    private int controlBehavior = RuleConstant.CONTROL_BEHAVIOR_DEFAULT;

    private TrafficShapingController controller;

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
