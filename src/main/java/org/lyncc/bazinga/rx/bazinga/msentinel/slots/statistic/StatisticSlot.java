package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic;

import jdk.nashorn.internal.ir.Block;
import org.lyncc.bazinga.rx.bazinga.msentinel.Constants;
import org.lyncc.bazinga.rx.bazinga.msentinel.EntryType;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.AbstractLinkedProcessorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ProcessorSlotEntryCallback;
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
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count, boolean priortized, Object... args) throws Throwable {

        try{
            fireEntry(context,resourceWrapper,node,count,priortized,args);

            // 增加一个线程数
            node.increaseThreadNum();
            // 增加一个通过的请求
            node.addPassRequest();

            if(context.getCurEntry().getOriginNode() != null){
                // Add count for origin node.
                context.getCurEntry().getOriginNode().increaseThreadNum();
                context.getCurEntry().getOriginNode().addPassRequest();
            }

            if (resourceWrapper.getType() == EntryType.IN) {
                // Add count for global inbound entry node for global statistics.
                Constants.ENTRY_NODE.increaseThreadNum();
                Constants.ENTRY_NODE.addPassRequest();
            }

            for (ProcessorSlotEntryCallback<DefaultNode> handler :StatisticSlotCallbackRegistry.getEntryCallbacks()){
                handler.onPass(context,resourceWrapper,node,count,args);
            }
        }catch (BlockException e){

            context.getCurEntry().setError(e);
            node.increaseBlockQps();

            if (resourceWrapper.getType() == EntryType.IN) {
                // Add count for global inbound entry node for global statistics.
                Constants.ENTRY_NODE.increaseBlockQps();
            }

            for (ProcessorSlotEntryCallback<DefaultNode> handler : StatisticSlotCallbackRegistry.getEntryCallbacks()) {
                handler.onBlocked(e, context, resourceWrapper, node, count, args);
            }

            throw e;

        }catch (Throwable e){
            context.getCurEntry().setError(e);

            // This should not happen.
            node.increaseExceptionQps();
            if (context.getCurEntry().getOriginNode() != null) {
                context.getCurEntry().getOriginNode().increaseExceptionQps();
            }

            if (resourceWrapper.getType() == EntryType.IN) {
                Constants.ENTRY_NODE.increaseExceptionQps();
            }
            throw e;
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
