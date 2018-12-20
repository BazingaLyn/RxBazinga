package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block;

public final class RuleConstant {

    public static final int FLOW_GRADE_THREAD = 0;

    public static final int FLOW_GRADE_QPS = 1;

    // 直调
    public static final int STRATEGY_DIRECT = 0;

    // 关联
    public static final int STRATEGY_RELATE = 1;

    // 链式
    public static final int STRATEGY_CHAIN = 2;

    public static final int CONTROL_BEHAVIOR_DEFAULT = 0;
    public static final int CONTROL_BEHAVIOR_WARM_UP= 1;
    public static final int CONTROL_BEHAVIOR_RATE_LIMITER = 2;
    public static final int CONTROL_BEHAVIOR_WARM_UP_RATE_LIMITER = 3;
    public static final String LIMIT_APP_DEFAULT = "default";
}
