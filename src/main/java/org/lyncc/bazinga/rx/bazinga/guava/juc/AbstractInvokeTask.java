package org.lyncc.bazinga.rx.bazinga.guava.juc;

import java.util.concurrent.Callable;

/**
 * 测试类
 *
 * @author liguolin
 * @create 2018-01-16 17:14
 **/
public abstract class AbstractInvokeTask<T> implements Callable<T> {


    @Override
    public abstract T call() throws Exception;
}
