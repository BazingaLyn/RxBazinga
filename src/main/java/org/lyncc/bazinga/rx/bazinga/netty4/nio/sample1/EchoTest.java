package org.lyncc.bazinga.rx.bazinga.netty4.nio.sample1;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author liguolin
 * @create 2018-06-28 17:41
 **/
public class EchoTest {

    Process server;
    EchoClient client;


    @Test
    public void givenServerClient_whenServerEchosMessage_thenCorrect() throws IOException, InterruptedException {
        server = EchoServer.start();
        client = EchoClient.start();
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        server.destroy();
        EchoClient.stop();
    }

}
