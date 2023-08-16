package com.csy.interview.no2;

import com.csy.interview.no2.nio_ext.MyClient;
import com.csy.interview.no2.nio_ext.MyServer;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/8/16
 */
public class SelectorTest {

    /**
     * Selector_选择器使用
     * 1）A selector provides a mechanism for monitoring one or more NIO channels and recognizing when one or more become available for data transfer.
     *   （选择器用提供一种机制，用来监控一个或多个NIO通道，并能识别何时一个或多个通道可用于传输）
     *
     * 2）This way, a single thread can be used for managing multiple channels, and thus multiple network connections.
     *   （按这种方式，一个单一的线程就可以用于管理多个通道，从而管理多个网络连接）
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-nio-selector
     */

    @Test
    public void test_server() { //启动服务端
        MyServer myServer = new MyServer();
        myServer.startServer();

    }

    @Test
    public void test_client() { //启动客户端
        MyClient myClient = new MyClient();
        myClient.startClient();
    }
}
