package org.lyncc.bazinga.rx.bazinga.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FlowTest1 {


    private static Logger logger = LoggerFactory.getLogger(FlowTest1.class);

    private static final String KEY = "abc";

    private static final String KEY2 = "abc2";

    public static void main(String[] args) throws InterruptedException {

        List<FlowRule> rules = new ArrayList<FlowRule>();

        FlowRule rule1 = new FlowRule();
        rule1.setResource(KEY);

        rule1.setCount(3);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);

        FlowRule rule2 = new FlowRule();
        rule2.setResource(KEY2);

        rule2.setCount(4);
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setLimitApp("default");
        rules.add(rule2);

        FlowRuleManager.loadRules(rules);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1; i++) {

                Entry entry = null;
                try {
                    entry = SphU.entry(KEY);
                    logger.info("hello in....");
                } catch (BlockException e) {
                    logger.info("hello not in....");
                }finally {
                    if(entry != null){
                        entry.exit();
                    }
                }

            }
        });

        thread.start();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1; i++) {

                Entry entry = null;
                try {
                    entry = SphU.entry(KEY2);
                    logger.info("hello2 in....");
                } catch (BlockException e) {
                    logger.info("hello2 not in....");
                }  finally {
                    if(entry != null){
                        entry.exit();
                    }
                }

            }
        });

        thread1.start();




    }
}
