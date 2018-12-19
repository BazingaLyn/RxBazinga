package org.lyncc.bazinga.rx.bazinga.listenermap;

import java.util.*;

public class BazingaMap<K,V> implements Map<K,V>,AbstractBazingaMap<K,V> {


    private Map<K,V> maps = null;
    private List<BazingaListener> bazingaListenerList;

    public BazingaMap(){
        maps = new HashMap<>();
        bazingaListenerList = new ArrayList<>();
    }

    @Override
    public int size() {
        return maps.size();
    }

    @Override
    public boolean isEmpty() {
        return maps.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return maps.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return maps.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return maps.get(key);
    }

    @Override
    public V put(K key, V value) {
        V o =  maps.put(key,value);
        if(null == o){
            for(BazingaListener bazingaListener : bazingaListenerList){
                bazingaListener.configLoad(key,value);
            }
        }else{
            for(BazingaListener bazingaListener : bazingaListenerList){
                bazingaListener.configUpdate(key,o,value);
            }
        }

        return o;
    }

    @Override
    public V remove(Object key) {

        V v = maps.remove(key);
        for(BazingaListener bazingaListener : bazingaListenerList){
            bazingaListener.configUpdate(key,v,null);
        }
        return v;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        if (m == null) {
            return;
        }

        Set<? extends Entry<? extends K, ? extends V>> entries = m.entrySet();

        for(Entry<? extends K, ? extends V> entry : entries){
            this.put(entry.getKey(),entry.getValue());
        }
    }

    @Override
    public void clear() {

        if(maps.keySet().size() == 0){
            return;
        }

        for(K key : maps.keySet()){
            remove(key);
        }

    }

    @Override
    public Set<K> keySet() {
        return maps.keySet();
    }

    @Override
    public Collection<V> values() {
        return maps.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return maps.entrySet();
    }

    @Override
    public void addListener(BazingaListener<K, V> bazingaListener) {
        this.bazingaListenerList.add(bazingaListener);
    }

    @Override
    public void removeListener(BazingaListener<K, V> bazingaListener) {
        this.bazingaListenerList.remove(bazingaListener);
    }

}
