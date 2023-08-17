package com.csy.interview.no2;

import com.csy.interview.no2.nio_ext.EchoClient;
import com.csy.interview.no2.nio_ext.EchoServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author chensy
 * @date 2023/8/17
 */
public class EchoTest {
    Process server;
    EchoClient client;

    @Before
    public void setup() throws IOException, InterruptedException {
        server = EchoServer.start();
        client = EchoClient.start();
    }

    @Test
    public void test_client_server() {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        assertEquals("hello", resp1);
        assertEquals("world", resp2);

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点：todo @csy-08/17
         * 1）为什么直接运行报出 Connection refused，而debug运行结果却是正常
         */
    }

    @After
    public void teardown() throws IOException {
        server.destroy();
        EchoClient.stop();
    }
}
