package org.lyncc.bazinga.rx.bazinga.jdk8.base;

/**
 * @author liguolin
 * @create 2018-08-09 9:41
 **/
public abstract class HelloService {

    public String sayHello(BaseObject o){

        System.out.println("hello baseObject");
        return saySpecialHello(o);

    }

    protected abstract String saySpecialHello(BaseObject o);
}
