package org.lyncc.bazinga.rx.bazinga.future.async;

import java.util.concurrent.ExecutionException;

/**
 * @author liguolin
 * @create 2018-09-27 14:08
 **/
public interface AsyncResult<T> {


    boolean isCompleted();


    T getValue() throws ExecutionException;


    void await() throws InterruptedException;
}
