package org.lyncc.bazinga.rx.bazinga.proxy;

/**
 * Created by liguolin on 2017/7/27.
 */
public class DefaultProxyFactory<T> implements ProxyFactory<T> {


    private Class<T> interfaceClass;


    @Override
    public Class<T> getDefaultClass() {
        return interfaceClass;
    }

    @Override
    public Object createHandler() {
        return new Invoker();
    }

    public static <I> DefaultProxyFactory<I> factory(Class<I> interfaceClass) {
        DefaultProxyFactory<I> factory = new DefaultProxyFactory<>(interfaceClass);

        return factory;
    }


    private DefaultProxyFactory(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }



}
