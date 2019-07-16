package org.lyncc.bazinga.rx.bazinga.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPrint2 {

    static AtomicInteger casNum = new AtomicInteger(0);
    static volatile  boolean flag = false;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
           while(casNum.get() < 1000){
               if(!flag && (casNum.get() == 0 || casNum.incrementAndGet() % 2 ==0)){
                   try {
                       Thread.sleep(200l);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println(casNum.get());
                   flag = true;
               }
           }

        });



        Thread thread1 = new Thread(() -> {
            while(casNum.get()  < 1000){
                if(flag && (casNum.incrementAndGet() % 2 !=0)){
                    try {
                        Thread.sleep(200l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(casNum.get());
                    flag = false;
                }
            }
        });

        thread.start();
        thread1.start();
    }


}
