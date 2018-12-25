package org.lyncc.bazinga.rx.bazinga.msentinel;


import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.ContextUtil;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.NullContext;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ProcessorSlotChain;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.StringResourceWrapper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liguolin
 * @create 2018-12-21 11:09
 **/
public class CtSph implements Sph {

    private static volatile Map<ResourceWrapper,ProcessorSlotChain> chainMap = new HashMap<>();

    private static final Object LOCK = new Object();


    @Override
    public Entry entry(String name) {
        return null;
    }

    @Override
    public Entry entry(Method method) {
        return null;
    }

    @Override
    public Entry entry(String name, EntryType type, int count, Object... args) {
        StringResourceWrapper resource = new StringResourceWrapper(name, type);
        return entry(resource, count, args);
    }

    private Entry entry(StringResourceWrapper resource, int count, Object[] args) {
        return entryWithPriority(resource, count, false, args);
    }

    private Entry entryWithPriority(StringResourceWrapper resource, int count, boolean b, Object[] args) {

        Context context = ContextUtil.getContext();
        if(context instanceof NullContext){

        }

        return null;
    }
}
