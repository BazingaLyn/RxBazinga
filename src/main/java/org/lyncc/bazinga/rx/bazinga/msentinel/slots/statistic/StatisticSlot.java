package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic;

import jdk.nashorn.internal.ir.Block;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.AbstractLinkedProcessorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.BlockException;

/**
 * @author liguolin
 * @create 2018-12-25 15:00
 **/
public class StatisticSlot extends AbstractLinkedProcessorSlot<DefaultNode> {

    /**
     * @param context
     * @param resourceWrapper
     * @param node
     * @param count
     * @param priortized
     * @param args
     */
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count, boolean priortized, Object... args) {

        try{
            fireEntry(context,resourceWrapper,node,count,priortized,args);

            node.increaseThreadNum();

        }catch (BlockException e){

        }catch (Throwable e){

        }
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
