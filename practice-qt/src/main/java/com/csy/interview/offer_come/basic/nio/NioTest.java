package com.csy.interview.offer_come.basic.nio;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/20
 */
public class NioTest {

    /**
     * NIO客户端和服务端通信测试
     * 1）要将启动服务端、客户端的方法分开启动，放同一个方法里面，模拟不了客户端、服务端的请求
     *
     * 2）分别在Client、Server类中使用main方法，也可以测试，但要模拟多客户端时，就得写多个测试类。
     * 而使用junit，只需写多个方法即可，更加方便简介
     */

    @Test
    public void testOpenServer() throws Exception {
        NioServer myServer = new NioServer(9999);
        myServer.listener();

        /**
         * 输出结果：
         * Nio Server bind port:9999
         * 服务端_收到消息：hello server，时间：1711327095044
         *
         * 结果分析：
         * 1）先通过ServerSocketChannel.open()创建服务端ServerSocketChannel
         * 2）再绑定端口 serverSocketChannel.bind(new InetSocketAddress(port));
         * 3）创建Selector，Selector.open();
         * 4）将Channel注册到Selector，并指定感兴趣的SelectorKey，如serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
         * 5）为Buffer分配空间，ByteBuffer.allocate(size);
         * 6）循环获取selector.selectedKeys()，根据key类型做对应的业务处理。
         *
         * 总结概括：
         *
         *
         * 问题点答疑：
         * 1）服务端通过SocketChannel channel = server.accept();获取到的SocketChannel是与客服端创建的对象是同一个吗？怎么得到的？
         */
    }

    @Test
    public void testOpenClient() throws Exception {
        NioClient nioClient = new NioClient();
        nioClient.connectServer();
        nioClient.send("hello server".getBytes());
        nioClient.receive();

        /**
         * 输出结果：
         * 客户端_收到消息：hello client，时间：1711327095043
         *
         * 结果分析：
         *
         * 总结概括：
         * 1）先通过socketChannel.open(); 创建客户端SocketChannel
         * 2）连接到服务端，socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
         * 3）为Buffer分配空间，ByteBuffer.allocate(size);
         * 4）发送消息：socketChannel.write(byteBuffer); 往通道中写ByteBuffer
         * 5）接收消息：socketChannel.read(byteBuffer); 从通道中读取内容到ByteBuffer
         */
    }
}
