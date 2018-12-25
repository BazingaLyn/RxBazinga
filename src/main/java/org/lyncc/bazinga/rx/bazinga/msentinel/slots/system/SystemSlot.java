package org.lyncc.bazinga.rx.bazinga.msentinel.slots.system;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.AbstractLinkedProcessorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;

/**
 * @author liguolin
 * @create 2018-12-25 15:01
 **/
public class SystemSlot extends AbstractLinkedProcessorSlot<DefaultNode> {
    /**
     * @param context
     * @param resourceWrapper
     * @param param
     * @param count
     * @param priortized
     * @param args
     */
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode param, int count, boolean priortized, Object... args) {

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
