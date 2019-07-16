package org.lyncc.bazinga.rx.bazinga.threadlocal;

public class ThreadLocalTest {

    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        String s = stringThreadLocal.get();
        Integer integer = integerThreadLocal.get();
    }



}
