package org.lyncc.bazinga.rx.bazinga.jdk8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * complatableFuture
 *
 * @author liguolin
 * @create 2018-01-10 18:13
 **/
public class CompletableFutureMain_Base2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
            }
            futurePrice.complete(23.5);
        }).start();

        System.out.println(futurePrice.get()); // 同步阻塞
        System.out.println(3333);

    }


}
