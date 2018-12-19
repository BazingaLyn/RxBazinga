package org.lyncc.bazinga.rx.bazinga.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FlowQpsDemo {


    private static final String KEY = "abc";

    private static AtomicInteger pass = new AtomicInteger();
    private static AtomicInteger block = new AtomicInteger();
    private static AtomicInteger total = new AtomicInteger();

    private static volatile  boolean stop = false;

    private static final int threadCount = 32;

    private static int seconds = 60 + 40;

    public static void main(String[] args) {

        initFlowQpsRule();

        tick();

        simulateTraffic();

        System.out.println("===== begin to do flow control");
        System.out.println("only 20 requests per second can pass");
    }

    private static void simulateTraffic(){
        for (int i = 0; i < threadCount; i++) {

            Thread t = new Thread(new RunTask());
            t.setName("simulate-traffic-task");
            t.start();
        }
    }

    private static void initFlowQpsRule() {

        List<FlowRule> rules = new ArrayList<FlowRule>();

        FlowRule rule1 = new FlowRule();
        rule1.setResource(KEY);

        rule1.setCount(20);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);

        FlowRuleManager.loadRules(rules);
    }


    private static void tick(){
        Thread timer = new Thread(new TimeTask());
        timer.setName("sentinel-timer-task");
        timer.start();
    }


    static class RunTask implements Runnable {

        @Override
        public void run() {
            while (!stop){
                Entry entry = null;

                try {
                    entry = SphU.entry(KEY);
                    pass.addAndGet(1);
                } catch (BlockException e) {
                    block.incrementAndGet();
                }finally {
                    total.incrementAndGet();
                    if(entry != null){
                        entry.exit();
                    }
                }
            }

        }
    }

    static class TimeTask implements Runnable {

        @Override
        public void run() {

            long start = System.currentTimeMillis();
            System.out.println("Begin to statistic!!!");

            long oldTotal = 0;
            long oldPass = 0;
            long oldBlock = 0;

            while(!stop){

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }

                long globalTotal = total.get();
                long oneSecondTotal = globalTotal - oldTotal;
                oldTotal = globalTotal;

                long globalPass = pass.get();
                long oneSecondPass = globalPass - oldPass;
                oldPass = globalPass;

                long globalBlock = block.get();
                long oneSecondBlock = globalBlock - oldBlock;
                oldBlock = globalBlock;

                System.out.println(seconds + " send qps is: " + oneSecondTotal);
                System.out.println(TimeUtil.currentTimeMillis() + ", total:" + oneSecondTotal
                        + ", pass:" + oneSecondPass
                        + ", block:" + oneSecondBlock);

                if (seconds-- <= 0) {
                    stop = true;
                }
            }

            long cost = System.currentTimeMillis() - start;
            System.out.println("time cost: " + cost + " ms");
            System.out.println("total:" + total.get() + ", pass:" + pass.get()
                    + ", block:" + block.get());
            System.exit(0);
        }
    }
}
