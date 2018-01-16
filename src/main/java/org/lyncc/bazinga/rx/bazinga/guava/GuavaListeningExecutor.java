package org.lyncc.bazinga.rx.bazinga.guava;

import com.google.common.util.concurrent.*;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * guava 异步线程服务类
 *
 * @author liguolin
 * @create 2018-01-16 17:11
 **/
public class GuavaListeningExecutor {

    private static ListeningExecutorService LISTENING_EXECUTOR_SERVICE;

    static {
        LISTENING_EXECUTOR_SERVICE = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
    }


    public static void main(String[] args) {

        StringInvokeTask stringInvokeTask = new StringInvokeTask();

        ListenableFuture<String> listenableFuture = LISTENING_EXECUTOR_SERVICE.submit(stringInvokeTask);

        Futures.addCallback(listenableFuture, new FutureCallback<String>() {

            @Override
            public void onSuccess(String s) {
                System.out.println(s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });



    }
}
