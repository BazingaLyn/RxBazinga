package org.lyncc.bazinga.rx.bazinga.msentinel.node;

import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;

/**
 * @author liguolin
 * @create 2018-12-21 11:03
 **/
public interface NodeBuilder {


    DefaultNode buildTreeNode(ResourceWrapper id,ClusterNode clusterNode);


    ClusterNode buildClusterNode();
}
