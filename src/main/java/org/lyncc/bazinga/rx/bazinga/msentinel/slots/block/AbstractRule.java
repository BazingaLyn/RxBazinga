package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block;

public abstract class AbstractRule implements Rule {

    private String resource;


    private String limitApp;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getLimitApp() {
        return limitApp;
    }

    public void setLimitApp(String limitApp) {
        this.limitApp = limitApp;
    }
}
