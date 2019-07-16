package org.lyncc.bazinga.rx.bazinga.thread;

public class ThreadPrint {

    public static void main(String[] args) {
        ThreadPrint threadPrint = new ThreadPrint();
        Thread thread1 = new Thread(threadPrint::print1);
        Thread thread2 = new Thread(threadPrint::print2);
        thread1.start();
        thread2.start();
    }

    public synchronized  void print1(){
        for (int i = 0; i < 1000; i+=2) {
            System.out.println(Thread.currentThread() +"======>" +i);
            this.notify();
            try{
                this.wait();
                Thread.sleep(200l);
            }catch (Exception e){
            }
        }
    }

    public synchronized  void print2(){
        for (int i = 1; i < 1000; i+=2) {
            System.out.println(Thread.currentThread() +"======>" +i);
            this.notify();
            try{
                this.wait();
                Thread.sleep(200l);
            }catch (Exception e){
            }
        }
    }
}
