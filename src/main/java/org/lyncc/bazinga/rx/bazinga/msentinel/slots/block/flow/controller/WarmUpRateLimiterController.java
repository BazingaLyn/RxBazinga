package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.controller;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.Node;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.TrafficShapingController;

/**
 * @author liguolin
 * @create 2018-12-20 20:21
 **/
public class WarmUpRateLimiterController implements TrafficShapingController {


    public WarmUpRateLimiterController(double count, int warmUpPeriodSec, int maxQueueingTimeMs, int coldFactor) {
    }

    @Override
    public boolean canPass(Node node, int acquireCount, boolean prioritized) {
        return false;
    }

    @Override
    public boolean canPass(Node node, int acquiredCount) {
        return false;
    }
}
