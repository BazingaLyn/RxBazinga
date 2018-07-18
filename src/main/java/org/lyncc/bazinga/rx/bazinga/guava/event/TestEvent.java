package org.lyncc.bazinga.rx.bazinga.guava.event;

/**
 * @author liguolin
 * @create 2018-06-19 15:46
 **/
public class TestEvent {

    private final int message;
    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }
    public int getMessage() {
        return message;
    }
}
