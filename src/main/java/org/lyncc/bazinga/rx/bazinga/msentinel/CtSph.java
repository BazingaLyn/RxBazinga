package org.lyncc.bazinga.rx.bazinga.msentinel;


import com.alibaba.csp.sentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.ContextUtil;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.NullContext;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ProcessorSlotChain;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.SlotChainProvider;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.StringResourceWrapper;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.BlockException;

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
    public Entry entry(String name, EntryType type, int count, Object... args) throws BlockException {
        StringResourceWrapper resource = new StringResourceWrapper(name, type);
        return entry(resource, count, args);
    }

    private Entry entry(StringResourceWrapper resource, int count, Object[] args) throws BlockException {
        return entryWithPriority(resource, count, false, args);
    }

    private Entry entryWithPriority(StringResourceWrapper resourceWrapper, int count, boolean prioritized, Object[] args) throws BlockException {

        Context context = ContextUtil.getContext();
        if(context instanceof NullContext){
            return new CtEntry(resourceWrapper, null, context);
        }

        if(context == null){
            context = MyContextUtil.myEntry(Constants.CONTEXT_DEFAULT_NAME,"",resourceWrapper.getType());
        }

        if(!Constants.ON){
            return new CtEntry(resourceWrapper, null, context);
        }

        ProcessorSlotChain<Object> chain = lookProcessorChain(resourceWrapper);

        if(chain == null){
            return new CtEntry(resourceWrapper,null,context);
        }

        Entry e = new CtEntry(resourceWrapper,chain,context);
        try{

            chain.entry(context,resourceWrapper,null,count,prioritized,args);
        }catch (BlockException e1){
            e.exit(count,args);
            throw e1;
        }catch (Throwable e1){
            RecordLog.info("Sentinel unexpected exception", e1);
        }



        return e;
    }

    private ProcessorSlotChain<Object> lookProcessorChain(StringResourceWrapper resourceWrapper) {
        ProcessorSlotChain chain = chainMap.get(resourceWrapper);

        if(chain == null){
            synchronized (LOCK){
                chain = chainMap.get(resourceWrapper);

                if(chain == null){
                    if (chainMap.size() >= Constants.MAX_SLOT_CHAIN_SIZE) {
                        return null;
                    }

                    chain = SlotChainProvider.newSlotChain();
                    Map<ResourceWrapper,ProcessorSlotChain> newMap = new HashMap<>(chainMap.size() + 1);
                    newMap.putAll(chainMap);
                    newMap.put(resourceWrapper, chain);
                    chainMap = newMap;
                }
            }
        }
        return chain;
    }


    private final static class MyContextUtil extends ContextUtil {

        static Context myEntry(String name, String origin, EntryType type){
            return trueEnter(name,origin);
        }

    }
}
