package org.lyncc.bazinga.rx.bazinga.msentinel.property;

import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DynamicSentinelProperty<T> implements SentinelProperty<T> {


    protected Set<PropertyListener<T>> listeners = Collections.synchronizedSet(new HashSet<PropertyListener<T>>());

    private T value = null;

    public DynamicSentinelProperty() {
    }

    public DynamicSentinelProperty(T value) {
        super();
        this.value = value;
    }

    @Override
    public void addListener(PropertyListener<T> listener) {
        listeners.add(listener);
        listener.configLoad(value);

    }

    @Override
    public void removeListener(PropertyListener<T> listener) {

        listeners.remove(listener);
    }

    @Override
    public void updateValue(T newValue) {

        if(isEqual(value,newValue)){
            return;
        }

        RecordLog.info("[DynamicSentinelProperty] Config will be updated to: " + newValue);

        value = newValue;
        for(PropertyListener<T> listener : listeners){
            listener.configUpdate(newValue);
        }
    }

    private boolean isEqual(T value, T newValue) {
        if(value == null && newValue == null){
            return true;
        }

        if(value == null){
            return false;
        }

        return value.equals(newValue);
    }
}
