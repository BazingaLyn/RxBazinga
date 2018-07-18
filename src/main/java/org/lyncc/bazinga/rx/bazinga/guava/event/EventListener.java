package org.lyncc.bazinga.rx.bazinga.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author liguolin
 * @create 2018-06-19 15:46
 **/
public class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
