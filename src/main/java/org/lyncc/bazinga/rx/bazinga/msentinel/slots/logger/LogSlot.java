package org.lyncc.bazinga.rx.bazinga.msentinel.slots.logger;

import com.alibaba.csp.sentinel.slots.logger.EagleEyeLogUtil;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.AbstractLinkedProcessorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.BlockException;

/**
 * @author liguolin
 * @create 2018-12-25 14:57
 **/
public class LogSlot extends AbstractLinkedProcessorSlot<DefaultNode> {



    /**
     * @param context
     * @param resourceWrapper
     * @param param
     * @param count
     * @param priortized
     * @param args
     */
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode param, int count, boolean priortized, Object... args) throws BlockException {

        try {
            fireEntry(context,resourceWrapper,param,count,priortized,args);
        } catch (BlockException e){
            EagleEyeLogUtil.log(resourceWrapper.getName(), e.getClass().getSimpleName(), e.getRuleLimitApp(),
                    context.getOrigin(), count);
            throw e;
        } catch (Throwable e) {
            RecordLog.info("Entry exception",e);
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
        try{
            fireExit(context,resourceWrapper,count,args);
        }catch (Throwable e){
            RecordLog.info("Entry exit exception",e);
        }
    }
}
