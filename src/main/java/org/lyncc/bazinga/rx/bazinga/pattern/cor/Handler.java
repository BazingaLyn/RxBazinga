package org.lyncc.bazinga.rx.bazinga.pattern.cor;

/**
 * @author liguolin
 * @create 2018-06-19 9:38
 **/
public abstract class Handler {

    protected Handler successor;


    public Handler(Handler successor) {
        this.successor = successor;
    }

    protected abstract void handleRequest(Request request);


}
