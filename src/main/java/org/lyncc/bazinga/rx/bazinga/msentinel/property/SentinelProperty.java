package org.lyncc.bazinga.rx.bazinga.msentinel.property;

public interface SentinelProperty<T> {

    void addListener(PropertyListener<T> listener);

    void removeListener(PropertyListener<T> listener);

    void updateValue(T newValue);
}
