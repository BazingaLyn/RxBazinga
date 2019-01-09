package org.lyncc.bazinga.rx.bazinga.msentinel.slots.degrade;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.AbstractLinkedProcessorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;

/**
 * @author liguolin
 * @create 2018-12-25 15:08
 **/
public class DegradeSlot extends AbstractLinkedProcessorSlot<DefaultNode> {
    /**
     * @param context
     * @param resourceWrapper
     * @param node
     * @param count
     * @param priortized
     * @param args
     */
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count, boolean priortized, Object... args) throws Throwable {

        DegradeRuleManager.checkDegrade(resourceWrapper,context,node,count);
        fireEntry(context,resourceWrapper,node,count,priortized,args);
    }

    /**
     * @param context
     * @param resourceWrapper
     * @param count
     * @param args
     */
    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }
}
