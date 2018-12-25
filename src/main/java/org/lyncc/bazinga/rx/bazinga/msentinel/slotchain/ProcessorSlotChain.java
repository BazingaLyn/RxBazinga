package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;

/**
 * @author liguolin
 * @create 2018-12-25 13:26
 **/
public abstract class ProcessorSlotChain<T> extends AbstractLinkedProcessorSlot<T> {


    public abstract void addFirst(AbstractLinkedProcessorSlot<T> abstractLinkedProcessorSlot);


    public abstract void addLast(AbstractLinkedProcessorSlot<T> abstractLinkedProcessorSlot);
}
