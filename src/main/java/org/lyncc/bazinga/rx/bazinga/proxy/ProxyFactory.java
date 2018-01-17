package org.lyncc.bazinga.rx.bazinga.proxy;

/**
 * Created by liguolin on 2017/7/27.
 */
public interface ProxyFactory<T> {

    default T newInstance(){

        Object handler = createHandler();

        Class<T> cls = getDefaultClass();

        return Proxies.getDefault().newProxy(cls, handler);

    }

    Class<T> getDefaultClass();

    Object createHandler();



}
