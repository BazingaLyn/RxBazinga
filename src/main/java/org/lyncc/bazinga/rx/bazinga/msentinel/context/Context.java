package org.lyncc.bazinga.rx.bazinga.msentinel.context;

import org.lyncc.bazinga.rx.bazinga.msentinel.Entry;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.Node;

public class Context {

    private final String name;

    private DefaultNode entranceNode;

    private Entry curEntry;

    private String origin = "";

    private final boolean async;


    public Node getLastNode(){
        if(curEntry != null && curEntry.getLastNode() != null){
            return curEntry.getLastNode();
        }else{
            return entranceNode;
        }
    }


    public Context(DefaultNode defaultNode,String name){
        this(name, defaultNode, false);
    }

    public Context(String name,DefaultNode defaultNode,boolean async){
        this.name = name;
        this.entranceNode = defaultNode;
        this.async = async;
    }

    public String getName() {
        return name;
    }

    public DefaultNode getEntranceNode() {
        return entranceNode;
    }

    public void setEntranceNode(DefaultNode entranceNode) {
        this.entranceNode = entranceNode;
    }

    public Entry getCurEntry() {
        return curEntry;
    }

    public void setCurEntry(Entry curEntry) {
        this.curEntry = curEntry;
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

    public Context setCurNode(DefaultNode node) {
        this.curEntry.setCurNode(node);
        return this;
    }
}
