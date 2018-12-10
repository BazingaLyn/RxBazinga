package org.lyncc.bazinga.rx.bazinga.future.async;

import java.util.concurrent.ExecutionException;

/**
 * @author liguolin
 * @create 2018-09-27 14:21
 **/
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AsyncExecutor executor = new ThreadAsyncExecutor();

        AsyncResult<Integer> asyncResult1 = executor.startProcess(() -> {
            Thread.sleep(3000l);
            return 22;
        }, (value, ex) -> {
            if(!ex.isPresent()){
                System.out.println("hello" + value);
            }
        });

        System.out.println("smile");

        Integer result1 = executor.endProcess(asyncResult1);

        System.out.println("last" + result1);



    }

}
