package org.lyncc.bazinga.rx.bazinga.proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

/**
 * Created by liguolin on 2017/7/27.
 */
public class Invoker {

    @RuntimeType
    public Object invoke(@Origin Method method,@Origin Class clazz, @AllArguments @RuntimeType Object[] args) throws Throwable {
        System.out.println("31321");
        return 111;
    }
}
