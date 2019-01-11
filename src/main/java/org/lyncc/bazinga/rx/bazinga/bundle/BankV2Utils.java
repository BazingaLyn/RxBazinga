package org.lyncc.bazinga.rx.bazinga.bundle;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BankV2Utils {

    public static String getUrl(){
        StandardUrlClassLoader classLoader = new StandardUrlClassLoader("v2");
        try {
            Class<?> aClass = classLoader.loadClass("com.bazinga.service.BaseService");
            Object o = aClass.newInstance();
            Method service = aClass.getMethod("service", null);
            return (String)service.invoke(o,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
