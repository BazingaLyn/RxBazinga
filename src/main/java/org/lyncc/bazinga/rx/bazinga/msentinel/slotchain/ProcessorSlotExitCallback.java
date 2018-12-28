package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;

/**
 * @author liguolin
 * @create 2018-12-28 18:31
 **/
public interface ProcessorSlotExitCallback {

    void onExit(Context context,ResourceWrapper resourceWrapper,int count,Object...args);
}
