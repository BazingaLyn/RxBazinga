package org.lyncc.bazinga.rx.bazinga.msentinel.property;

public interface PropertyListener<T> {


    void configUpdate(T value);


    void configLoad(T value);
}
