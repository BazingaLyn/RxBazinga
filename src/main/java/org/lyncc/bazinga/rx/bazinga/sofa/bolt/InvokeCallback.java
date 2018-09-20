package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import java.util.concurrent.Executor;

/**
 * @author liguolin
 * @create 2018-07-22 10:06
 **/
public interface InvokeCallback {


    void onResponse(final Object result);



    void onException(final Throwable e);



    Executor getExecutor();
}
