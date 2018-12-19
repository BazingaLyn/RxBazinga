package org.lyncc.bazinga.rx.bazinga.listenermap;

public interface BazingaListener<K,V> {


    void configUpdate(K key,V oldValue,V newValue);


    void configLoad(K key,V value);
}
