package org.lyncc.bazinga.rx.bazinga.future.async;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author liguolin
 * @create 2018-09-27 14:08
 **/
public interface AsyncExecutor {

    <T> AsyncResult<T> startProcess(Callable<T> task);

    <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback);

    <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException;
}
