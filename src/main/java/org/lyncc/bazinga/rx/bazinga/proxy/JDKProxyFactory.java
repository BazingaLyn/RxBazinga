package org.lyncc.bazinga.rx.bazinga.proxy;

public class JDKProxyFactory<T> implements ProxyFactory<T> {

    private Class<T> interfaceClass;

    public static <I> JDKProxyFactory<I> factory(Class<I> interfaceClass) {
        JDKProxyFactory<I> factory = new JDKProxyFactory<>(interfaceClass);

        return factory;
    }

    private JDKProxyFactory(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public T newInstance() {
        return Proxies.getJdkProxy().newProxy(interfaceClass, createHandler());
    }

    @Override
    public Class<T> getDefaultClass() {
        return interfaceClass;
    }

    @Override
    public Object createHandler() {
        return new JDKInvoke();
    }
}
