package org.lyncc.bazinga.rx.bazinga.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author liguolin
 * @create 2018-06-15 16:10
 **/
public class AtomicIntegerFieldUpdaterTest {

    static class Student {
        private String name;
        public volatile int age;

        public Student(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }
    }

    private static AtomicIntegerFieldUpdater<Student> fu = AtomicIntegerFieldUpdater
            .newUpdater(Student.class, "age");


    public static void main(String[] args) {
        Student st = new Student("张三", 0);
        List<Thread> v = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            ThreadA a = new ThreadA(st);
            Thread b = new Thread(a, "线程" + String.valueOf(i));
            v.add(b);
        }
        for (int i = 0; i < 5; i++) {
            Thread th = v.get(i);
            th.start();
        }
    }


    static class ThreadA implements Runnable {

        private Student st;


        public ThreadA(Student st) {
            super();
            this.st = st;
        }

        @Override
        public void run() {
            int expect = fu.get(st);
            int value = expect + 1;
            System.out.println(fu.get(st));
            if (fu.compareAndSet(st, expect, value)) {
                System.out.println("当前" + Thread.currentThread().getName()
                        + "执行新增成功之后的结果" + fu.get(st));
            } else {
                System.out.println(
                        "当前" + Thread.currentThread().getName() + "执行失败");
            }
        }
    }
}
