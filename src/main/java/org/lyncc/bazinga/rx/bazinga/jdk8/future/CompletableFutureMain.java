package org.lyncc.bazinga.rx.bazinga.jdk8.future;

import java.util.concurrent.CompletableFuture;

/**
 * 测试服务类main
 *
 * @author liguolin
 * @create 2018-01-15 14:36
 **/
public class CompletableFutureMain {

    public static void main(String[] args) {

        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        }).handleAsync((Integer value,Throwable e) -> {
            System.out.println("last compute is "+ value);
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("last compute is after"+ value);
            return 4;
        }).handleAsync((Integer value,Throwable e) -> {
            System.out.println("last2 compute is "+ value);
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("last2 compute is after"+ value);
            return 4;
        }).whenCompleteAsync((Integer value,Throwable e) -> {
            System.out.println("whenCompleteAsync compute is "+ value);
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("whenCompleteAsync compute is after"+ value);
        });

        System.out.println("current is hahahaha");
        try {
            Thread.sleep(40000l);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }
}
