package org.lyncc.bazinga.rx.bazinga.raft;

public interface Node<T> extends LifeCycle {

    void setConfig(NodeConfig nodeConfig);


}
