package org.lyncc.bazinga.rx.bazinga.msentinel.slots.cluster;

import org.lyncc.bazinga.rx.bazinga.msentinel.EntryType;
import org.lyncc.bazinga.rx.bazinga.msentinel.Env;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.ClusterNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.Node;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.AbstractLinkedProcessorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.StringResourceWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liguolin
 * @create 2018-12-25 14:59
 **/
public class ClusterBuilderSlot extends AbstractLinkedProcessorSlot<DefaultNode> {



    private static volatile Map<ResourceWrapper,ClusterNode> clusterNodeMap = new HashMap<>();

    private static final Object lock = new Object();

    private ClusterNode clusterNode = null;

    public static ClusterNode getClusterNode(String resource) {
        if(resource == null){
            return null;
        }

        ClusterNode clusterNode = null;
        for (EntryType nodeType : EntryType.values()) {
            clusterNode = clusterNodeMap.get(new StringResourceWrapper(resource, nodeType));
            if (clusterNode != null) {
                break;
            }
        }

        return clusterNode;
    }

    /**
     * @param context
     * @param resourceWrapper
     * @param node
     * @param count
     * @param prioritized
     * @param args
     */
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count, boolean prioritized, Object... args) throws Throwable {

        if(clusterNode == null){
            synchronized (lock){
                if(clusterNode == null){

                    clusterNode = Env.nodeBuilder.buildClusterNode();
                    HashMap<ResourceWrapper, ClusterNode> newMap = new HashMap<ResourceWrapper, ClusterNode>(16);
                    newMap.putAll(clusterNodeMap);
                    newMap.put(node.getResourceWrapper(), clusterNode);
                    clusterNodeMap = newMap;
                }
            }
        }
        node.setClusterNode(clusterNode);
        if(!"".equals(context.getOrigin())){
            Node originNode = node.getClusterNode().getOrCreateOriginNode(context.getOrigin());
            context.getCurEntry().setOriginNode(originNode);
        }
        fireEntry(context,resourceWrapper,node,count,prioritized,args);
    }

    /**
     * @param context
     * @param resourceWrapper
     * @param count
     * @param args
     */
    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {
        fireExit(context,resourceWrapper,count,args);
    }

    public static ClusterNode getClusterNode(String name,EntryType type){
        return clusterNodeMap.get(new StringResourceWrapper(name,type));
    }

    public static Map<ResourceWrapper, ClusterNode> getClusterNodeMap() {
        return clusterNodeMap;
    }
}
