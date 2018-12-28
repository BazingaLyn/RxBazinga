package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.BlockException;

/**
 * @author liguolin
 * @create 2018-12-28 18:25
 **/
public interface ProcessorSlotEntryCallback<T> {

    void onPass(Context context, ResourceWrapper resourceWrapper, T params, int count, Object... args) throws Exception;

    void onBlocked(BlockException exception,Context context,ResourceWrapper resourceWrapper,T params,int count ,Object...args);
}
