package org.lyncc.bazinga.rx.bazinga.pattern.cor;

/**
 * @author liguolin
 * @create 2018-06-19 9:39
 **/
public class Request {

    public enum RequestType {
        type1, type2
    }

    private RequestType type;
    private String name;

    public Request(RequestType type, String name) {
        this.type = type;
        this.name = name;
    }

    public RequestType getType() {
        return type;
    }

    public String getName() {
        return name;
    }


}


