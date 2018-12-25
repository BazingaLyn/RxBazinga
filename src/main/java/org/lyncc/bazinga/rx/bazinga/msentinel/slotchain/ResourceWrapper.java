package org.lyncc.bazinga.rx.bazinga.msentinel.slotchain;

import org.lyncc.bazinga.rx.bazinga.msentinel.EntryType;

/**
 * @author liguolin
 * @create 2018-12-21 10:50
 **/
public abstract class ResourceWrapper {

    protected String name;

    protected EntryType type = EntryType.OUT;

    public abstract String getName();

    public abstract String getShowName();

    public abstract EntryType getType();

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResourceWrapper) {
            ResourceWrapper rw = (ResourceWrapper)obj;
            return rw.getName().equals(getName());
        }
        return false;
    }
}
