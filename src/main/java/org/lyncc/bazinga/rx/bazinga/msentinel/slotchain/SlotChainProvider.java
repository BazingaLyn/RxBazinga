package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;



import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.DefaultSlotChainBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author liguolin
 * @create 2018-12-25 14:30
 **/
public class SlotChainProvider {

    private static volatile SlotChainBuilder builder = null;

    private static final ServiceLoader<SlotChainBuilder> LOADER = ServiceLoader.load(SlotChainBuilder.class);


    private SlotChainProvider() {}


    public static ProcessorSlotChain newSlotChain(){

        if(builder != null){
            return builder.build();
        }

        resolveSlotChainBuilder();

        if(builder == null){
            RecordLog.warn("[SlotChainProvider] Wrong state when resolving slot chain builder, using default");
            builder = new DefaultSlotChainBuilder();
        }

        return builder.build();
    }

    private static void resolveSlotChainBuilder() {

        List<SlotChainBuilder> list = new ArrayList<>();
        boolean hasOther = false;

        for(SlotChainBuilder slotChainBuilder:LOADER){
            if(builder.getClass() != DefaultSlotChainBuilder.class){
                hasOther = true;
                list.add(builder);
            }
        }

        if(hasOther){
            builder = list.get(0);
        }else {
            // No custom builder, using default.
            builder = new DefaultSlotChainBuilder();
        }

        RecordLog.info("[SlotChainProvider] Global slot chain builder resolved: "
                + builder.getClass().getCanonicalName());
    }
}
