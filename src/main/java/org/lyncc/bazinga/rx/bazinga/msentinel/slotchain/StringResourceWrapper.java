package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;


import org.lyncc.bazinga.rx.bazinga.msentinel.EntryType;

/**
 * @author liguolin
 * @create 2018-12-21 11:18
 **/
public class StringResourceWrapper extends ResourceWrapper {

    public StringResourceWrapper(String name, EntryType type) {
        if (name == null) {
            throw new IllegalArgumentException("Resource name cannot be null");
        }
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getShowName() {
        return name;
    }

    @Override
    public EntryType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "StringResourceWrapper{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
