package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;

/**
 * @author liguolin
 * @create 2018-12-25 13:28
 **/
public class DefaultProcessorSlotChain extends ProcessorSlotChain {

    AbstractLinkedProcessorSlot<?> first = new AbstractLinkedProcessorSlot<Object>() {
        @Override
        public void entry(Context context, ResourceWrapper resourceWrapper, Object param, int count, boolean priortized, Object... args) {
            super.fireEntry(context, resourceWrapper, param, count, priortized, args);
        }

        @Override
        public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {
            super.fireExit(context, resourceWrapper, count, args);
        }
    };

    AbstractLinkedProcessorSlot end = first;


    @Override
    public void addFirst(AbstractLinkedProcessorSlot abstractLinkedProcessorSlot) {
        abstractLinkedProcessorSlot.setNext(first.getNext());
        first.setNext(abstractLinkedProcessorSlot);
        if(end == first){
            end = abstractLinkedProcessorSlot;
        }
    }

    @Override
    public void addLast(AbstractLinkedProcessorSlot abstractLinkedProcessorSlot) {
        end.setNext(abstractLinkedProcessorSlot);
        end = abstractLinkedProcessorSlot;
    }

    @Override
    public void setNext(AbstractLinkedProcessorSlot next) {
        addLast(next);
    }

    @Override
    public AbstractLinkedProcessorSlot<?> getNext() {
        return first.getNext();
    }

    /**
     * @param context
     * @param resourceWrapper
     * @param param
     * @param count
     * @param priortized
     * @param args
     */
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, Object param, int count, boolean priortized, Object... args) {
        first.transformEntry(context, resourceWrapper, param, count, priortized, args);
    }

    /**
     * @param context
     * @param resourceWrapper
     * @param count
     * @param args
     */
    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {
        first.exit(context, resourceWrapper, count, args);
    }
}
