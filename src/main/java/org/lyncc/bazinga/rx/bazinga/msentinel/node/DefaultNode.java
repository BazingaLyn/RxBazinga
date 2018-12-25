package org.lyncc.bazinga.rx.bazinga.msentinel.node;

import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;

import java.util.HashSet;
import java.util.Set;

public class DefaultNode extends StatisticNode {

    private ResourceWrapper resourceWrapper;

    private volatile Set<Node> childList = new HashSet<>();

    private ClusterNode clusterNode;

    public DefaultNode(ResourceWrapper resourceWrapper,ClusterNode clusterNode){
        this.resourceWrapper = resourceWrapper;
        this.clusterNode = clusterNode;
    }


    public void addChild(DefaultNode node) {
        if(node == null){
            RecordLog.warn("Trying to add null child to node <{0}>, ignored", resourceWrapper.getName());
            return;
        }
        if(!childList.contains(node)){
            synchronized (this){
                if(!childList.contains(node)){
                    Set<Node> newSet = new HashSet<>(childList.size() + 1);
                    newSet.add(node);
                    childList = newSet;
                }
            }
            RecordLog.info("Add child <{0}> to node <{1}>",((DefaultNode)node).resourceWrapper.getName(), resourceWrapper.getName());
        }
    }

    @Override
    public void increaseThreadNum() {
        super.increaseThreadNum();
    }
}
