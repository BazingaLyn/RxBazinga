package org.lyncc.bazinga.rx.bazinga.pattern.cor;

/**
 * @author liguolin
 * @create 2018-06-19 9:40
 **/
public class ConcreteHandler1 extends Handler {


    public ConcreteHandler1(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == Request.RequestType.type1) {
            System.out.println(request.getName() + " is handle by ConcreteHandler1");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }

}
