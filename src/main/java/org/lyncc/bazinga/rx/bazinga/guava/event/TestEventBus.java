package org.lyncc.bazinga.rx.bazinga.guava.event;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author liguolin
 * @create 2018-06-19 15:48
 **/
public class TestEventBus {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());
    }
}
