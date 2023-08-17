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
     * 3）With a selector, we can use one thread instead of several to manage multiple channels. Context-switching（上下文切换） between threads is
     *   expensive for the operating system, and additionally（另外）, each thread takes up memory
     *
     * 4）Channel注册给Selector后，Selector就会对应监听，至于Channel放在Selector的数组中还是Map中，要看对应的实现，如KQueueSelectorImpl维护的是Map，PollSelectorImpl维护的是数组
     *
     * 5）Selector表示选择器或多路选择器，它的功能主要是通过轮训来检查多个通道的状态，判断通道中的事件是否发生，根据事件类型做出相应的响应。一个Selector完全可以用来管理多个连接，由此大大提升了系统的性能
     *
     * 6）Selector使用的步骤：
     *    a）创建Selector：使用Selector静态方法open()，即Selector.open();
     *    b）注册Channel的事件到Selector（因为Selector需要轮训多个Channel，所以注册的Channel必须是非阻塞的）
     *       b.1）channel.configureBlocking(false) //设置为非阻塞模式
     *       b.2）SelectorKey key = channel.registry(selector, SelectorKey.OP_WRITE); 将Channel的事件注册到Selector
     *    c）获取SelectorKey对象，这个表示特定通道对象与特定选择器对象之间的注册关系
     *    d）使用Selector选择Channel
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
