package org.lyncc.bazinga.rx.bazinga.msentinel.context;

import org.lyncc.bazinga.rx.bazinga.msentinel.Entry;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;

public class Context {

    private final String name;

    private DefaultNode defaultNode;

    private Entry entry;

    private String origin = "";

    private final boolean async;

    public Context(DefaultNode defaultNode,String name){
        this(name, defaultNode, false);
    }

    public Context(String name,DefaultNode defaultNode,boolean async){
        this.name = name;
        this.defaultNode = defaultNode;
        this.async = async;
    }

    public String getName() {
        return name;
    }

    public DefaultNode getDefaultNode() {
        return defaultNode;
    }

    public void setDefaultNode(DefaultNode defaultNode) {
        this.defaultNode = defaultNode;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isAsync() {
        return async;
    }
}
