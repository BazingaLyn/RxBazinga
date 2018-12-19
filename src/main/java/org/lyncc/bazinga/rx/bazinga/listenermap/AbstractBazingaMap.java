package org.lyncc.bazinga.rx.bazinga.listenermap;

public interface AbstractBazingaMap<K,V> {


    void addListener(BazingaListener<K,V> bazingaListener);


    void removeListener(BazingaListener<K,V> bazingaListener);

}
