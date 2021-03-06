package org.lyncc.bazinga.rx.bazinga.jdk8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * complatableFuture
 *
 * @author liguolin
 * @create 2018-01-10 18:13
 **/
public class CompletableFutureMain_Base1 {

    private static ExecutorService executors = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {


        CompletableFuture.runAsync(()->{

            try {
                Thread.sleep(3000l);
                System.out.println("hello world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },executors);


        System.out.println("hahahda");

//        CompletableFuture<Double> futurPrice = getPriceAsync();
//
//        System.out.println(111);
//
//        futurPrice.whenComplete(((aDouble, throwable) -> {
//            System.out.println(aDouble);
//        }));
//
//        System.out.println(222);

    }


    private static CompletableFuture<Double> getPriceAsync() {

        CompletableFuture<Double> future = new CompletableFuture<>();

        new Thread(() -> {
            try{
                Thread.sleep(5000);
            }catch (Exception e){
                //igonre
            }
            future.completedFuture(123.4d);
        }).start();
        
        return future;

    }
}
