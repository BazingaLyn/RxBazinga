package org.lyncc.bazinga.rx.bazinga.jdk8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * complatableFuture
 *
 * 初步感觉 complatableFuture 用于用另一个线程去处理 但是主线程依赖于另一个线程返回的结果
 *
 * @author liguolin
 * @create 2018-01-10 18:13
 **/
public class CompletableFutureMain_Base3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            if(true) {
                throw new RuntimeException();
            }
            futurePrice.complete(23.5);
        }).start();

        System.out.println(futurePrice.get());

    }


}
