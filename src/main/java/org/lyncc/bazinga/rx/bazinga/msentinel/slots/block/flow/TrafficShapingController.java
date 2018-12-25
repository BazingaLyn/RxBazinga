package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.flow;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.Node;

public interface TrafficShapingController {

    boolean canPass(Node node,int acquireCount,boolean prioritized);


    boolean canPass(Node node,int acquiredCount);
}
