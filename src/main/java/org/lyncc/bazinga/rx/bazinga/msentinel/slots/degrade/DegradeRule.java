package org.lyncc.bazinga.rx.bazinga.msentinel.slots.degrade;

import org.lyncc.bazinga.rx.bazinga.msentinel.concurrent.NamedThreadFactory;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.ClusterNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.AbstractRule;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.RuleConstant;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.cluster.ClusterBuilderSlot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class DegradeRule extends AbstractRule {

    private static final int RT_MAX_EXCEED_N = 5;


    private static ScheduledExecutorService pool = Executors.newScheduledThreadPool(
            Runtime.getRuntime().availableProcessors(),new NamedThreadFactory("sentinel-degrade-reset-task",true)
    );

    public DegradeRule(){

    }

    public DegradeRule(String resourceName){
        setResource(resourceName);
    }

    private double count;

    private int timeWindow;

    private int grade = RuleConstant.DEGRADE_GRADE_RT;

    private volatile boolean cut = false;

    public int getGrade(){
        return grade;
    }

    public DegradeRule setGrade(int grade){
        this.grade = grade;
        return this;
    }

    public DegradeRule setCount(double count){
        this.count = count;
        return this;
    }

    public void setCut(boolean cut) {
        this.cut = cut;
    }

    private AtomicLong passCount = new AtomicLong(0);

    public AtomicLong getPassCount() {
        return passCount;
    }

    @Override
    public boolean passCheck(Context context, DefaultNode node, int count, Object... args) {

        if(cut){
            return false;
        }

        ClusterNode clusterNode = ClusterBuilderSlot.getClusterNode(this.getResource());
        if(clusterNode == null){
            return true;
        }

        if(grade == RuleConstant.DEGRADE_GRADE_RT){
            double rt = clusterNode.avgRt();
            if(rt < this.count){
                passCount.set(0);
                return true;
            }

            if(passCount.incrementAndGet() < RT_MAX_EXCEED_N){
                return true;
            }
        }else if(grade == RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO){

        }


        return false;
    }

    public DegradeRule setTimeWindow(int timeWindow) {
        this.timeWindow = timeWindow;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DegradeRule)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        DegradeRule that = (DegradeRule)o;

        if (count != that.count) {
            return false;
        }
        if (timeWindow != that.timeWindow) {
            return false;
        }
        if (grade != that.grade) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + new Double(count).hashCode();
        result = 31 * result + timeWindow;
        result = 31 * result + grade;
        return result;
    }

    @Override
    public String toString() {
        return "DegradeRule{" +
                "resource=" + getResource() +
                ", grade=" + grade +
                ", count=" + count +
                ", limitApp=" + getLimitApp() +
                ", timeWindow=" + timeWindow +
                "}";
    }

    private static final class ResetTask implements Runnable {

        private DegradeRule rule;

        ResetTask(DegradeRule rule){
            this.rule = rule;
        }

        @Override
        public void run() {
            rule.getPassCount().set(0);
            rule.setCut(false);
        }
    }
}
