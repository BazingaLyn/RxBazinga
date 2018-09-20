package org.lyncc.bazinga.rx.bazinga.jdk8.base;

/**
 * @author liguolin
 * @create 2018-08-09 9:51
 **/
public class Main {

    public static void main(String[] args) {
        HelloService helloService = new HelloService() {
            @Override
            protected String saySpecialHello(BaseObject o) {
                return null;
            }
        };
    }
}
