package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.controller;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.Node;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow.TrafficShapingController;

/**
 * @author liguolin
 * @create 2018-12-20 20:18
 **/
public class DefaultController implements TrafficShapingController {

    public DefaultController(double count, int grade) {
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
