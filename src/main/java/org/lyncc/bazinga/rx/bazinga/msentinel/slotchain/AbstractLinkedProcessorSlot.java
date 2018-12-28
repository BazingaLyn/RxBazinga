package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;

/**
 * @author liguolin
 * @create 2018-12-25 13:17
 **/
public abstract class AbstractLinkedProcessorSlot<T> implements ProcessorSlot<T> {

    private AbstractLinkedProcessorSlot<?> next = null;

    @Override
    public void fireEntry(Context context, ResourceWrapper resourceWrapper, Object param, int count, boolean prioritized, Object... args) throws Throwable {

        if(next != null){
            next.transformEntry(context,resourceWrapper,param,count,prioritized,args);
        }
    }

    void transformEntry(Context context, ResourceWrapper resourceWrapper, Object param, int count, boolean prioritized, Object... args) throws Throwable {
        T t = (T)param;
        entry(context,resourceWrapper,t,count,prioritized,args);
    }

    @Override
    public void fireExit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {
        if(next != null){
            next.exit(context,resourceWrapper,count,args);
        }
    }

    public AbstractLinkedProcessorSlot<?> getNext() {
        return next;
    }

    public void setNext(AbstractLinkedProcessorSlot<?> next) {
        this.next = next;
    }
}
