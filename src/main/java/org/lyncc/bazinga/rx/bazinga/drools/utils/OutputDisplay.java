package org.lyncc.bazinga.rx.bazinga.drools.utils;

public class OutputDisplay {

    public OutputDisplay(){
    }

    public void showText(String someText){
        long time = System.currentTimeMillis();
        System.out.println("time= " + time + "-" + someText);
    }
}
