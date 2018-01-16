package org.lyncc.bazinga.rx.bazinga.jdk8;


import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * complatableFuture
 *
 * 初步感觉 complatableFuture 用于用另一个线程去处理 但是主线程依赖于另一个线程返回的结果
 *
 * @author liguolin
 * @create 2018-01-10 18:13
 **/
public class CompletableFutureMain_Base4 {

//    @Test
//    public void test01(){ //依赖于前面处理的结果
//        String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").join();
//        System.out.println(result);
//    }

    public Boolean test02(){ //依赖于前面处理的结果

        final Boolean[] lastFlag = {false};

        CompletableFuture<Boolean> futurPrice = getBooleanTest();

        futurPrice.handleAsync((flag, throwable) ->{
            lastFlag[0] = flag;
            return notifyMyself();
        }).thenApplyAsync(s -> {System.out.println(s+" world");
        return false;}).thenAccept((Boolean flag) -> System.out.println(flag));

        return lastFlag[0];





    }

    public String notifyMyself(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
        }
        return "hello";
    }

    public CompletableFuture<Boolean> getBooleanTest(){
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        try {
            Thread.sleep(4000l);
            System.out.println("Smile first");
        } catch (InterruptedException e) {
        }
        return future.completedFuture(Boolean.TRUE);
    }



    @Test
    public void test03(){
        System.out.println("result:"+test02());

        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
        }
    }




}
