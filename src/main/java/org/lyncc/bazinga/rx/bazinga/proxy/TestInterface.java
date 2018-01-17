package org.lyncc.bazinga.rx.bazinga.proxy;

/**
 * Created by liguolin on 2017/7/27.
 */
public class TestInterface {

    public static void main(String[] args) {
        Test test = DefaultProxyFactory.factory(Test.class).newInstance();
        Integer result = test.say();
        System.out.println(result);
    }
}
