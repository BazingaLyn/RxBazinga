package org.lyncc.bazinga.rx.bazinga.msentinel;

import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.BlockException;

/**
 * @author liguolin
 * @create 2018-12-21 10:44
 **/
public class SphU {

    private static final Object[] OBJECTS0 = new Object[0];


    public static Entry entry(String name) throws BlockException{
        return Env.sph.entry(name,EntryType.OUT,1,OBJECTS0);
    }

}
