package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow;


import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.RuleConstant;

import java.util.Comparator;

public class FlowRuleComparator implements Comparator<FlowRule> {

    @Override
    public int compare(FlowRule o1, FlowRule o2) {
        // Clustered mode will be on the top.
//        if (o1.isClusterMode() && !o2.isClusterMode()) {
//            return 1;
//        }
//
//        if (!o1.isClusterMode() && o2.isClusterMode()) {
//            return -1;
//        }

        if (o1.getLimitApp() == null) {
            return 0;
        }

        if (o1.getLimitApp().equals(o2.getLimitApp())) {
            return 0;
        }

        if (RuleConstant.LIMIT_APP_DEFAULT.equals(o1.getLimitApp())) {
            return 1;
        } else if (RuleConstant.LIMIT_APP_DEFAULT.equals(o2.getLimitApp())) {
            return -1;
        } else {
            return 0;
        }
    }
}
