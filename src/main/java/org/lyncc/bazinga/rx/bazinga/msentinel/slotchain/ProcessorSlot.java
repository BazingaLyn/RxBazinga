package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;

/**
 * @author liguolin
 * @create 2018-12-25 13:11
 **/
public interface ProcessorSlot<T> {

    /**
     *
     * @param context
     * @param resourceWrapper
     * @param param
     * @param count
     * @param priortized
     * @param args
     */
    void entry(Context context, ResourceWrapper resourceWrapper, T param, int count, boolean priortized, Object... args);


    /**
     *
     * @param context
     * @param resourceWrapper
     * @param param
     * @param count
     * @param priortized
     * @param args
     */
    void fireEntry(Context context, ResourceWrapper resourceWrapper, T param, int count, boolean priortized, Object... args);


    /**
     *
     * @param context
     * @param resourceWrapper
     * @param count
     * @param args
     */
    void exit(Context context,ResourceWrapper resourceWrapper,int count,Object... args);


    /**
     *
     * @param context
     * @param resourceWrapper
     * @param count
     * @param args
     */
    void fireExit(Context context,ResourceWrapper resourceWrapper,int count,Object... args);



}
