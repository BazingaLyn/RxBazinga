package org.lyncc.bazinga.rx.bazinga.msentinel.node;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liguolin
 * @create 2018-12-21 11:05
 **/
public class ClusterNode extends StatisticNode {

    private Map<String, StatisticNode> originCountMap = new HashMap<String, StatisticNode>();

    private final ReentrantLock lock = new ReentrantLock();
}
