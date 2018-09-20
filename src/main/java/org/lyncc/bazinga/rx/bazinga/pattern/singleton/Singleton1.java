package org.lyncc.bazinga.rx.bazinga.pattern.singleton;

/**
 * @author liguolin
 * @create 2018-06-29 14:34
 **/
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    private Singleton1 (){}

    public static Singleton1 getInstance() {
        return instance;
    }
}
