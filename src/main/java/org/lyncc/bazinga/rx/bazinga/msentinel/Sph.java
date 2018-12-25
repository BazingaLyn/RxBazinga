package org.lyncc.bazinga.rx.bazinga.msentinel;


import java.lang.reflect.Method;

/**
 * @author liguolin
 * @create 2018-12-21 11:09
 **/
public interface Sph {

    Entry entry(String name);

    Entry entry(Method method);

    Entry entry(String name, EntryType type, int count, Object... args)
}
