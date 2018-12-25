package org.lyncc.bazinga.rx.bazinga.msentinel.slots.nodeseletor;

import org.lyncc.bazinga.rx.bazinga.msentinel.Env;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.AbstractLinkedProcessorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liguolin
 * @create 2018-12-25 14:55
 **/
public class NodeSelectorSlot extends AbstractLinkedProcessorSlot<Object> {

    private volatile Map<String, DefaultNode> map = new HashMap<String, DefaultNode>(10);

    /**
     * @param context
     * @param resourceWrapper
     * @param param
     * @param count
     * @param prioritized
     * @param args
     */
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, Object param, int count, boolean prioritized, Object... args) throws Throwable {

        DefaultNode node = map.get(context.getName());
        if(node == null){
            synchronized (this){
                node = map.get(context.getName());
                if(node == null){

                    node = Env.nodeBuilder.buildTreeNode(resourceWrapper,null);
                    HashMap<String,DefaultNode> cacheMap = new HashMap<>(map.size());
                    cacheMap.putAll(map);
                    cacheMap.put(context.getName(), node);
                    map = cacheMap;
                }

                ((DefaultNode)context.getLastNode()).addChild(node);
            }
        }
        context.setCurNode(node);
        fireEntry(context, resourceWrapper, node, count, prioritized, args);
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
}
