package org.lyncc.bazinga.rx.bazinga.pattern.cor;

/**
 * @author liguolin
 * @create 2018-06-19 9:43
 **/
public class ConcreteHandler2 extends Handler{


    public ConcreteHandler2(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == Request.RequestType.type2) {
            System.out.println(request.getName() + " is handle by ConcreteHandler2");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
