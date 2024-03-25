package com.csy.interview.offer_come.basic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author chensy
 * @date 2024/1/5
 */
public class NioServer {

    Logger logger = LoggerFactory.getLogger(NioServer.class);

    private int size = 1024;

    private ServerSocketChannel serverSocketChannel;

    private ByteBuffer byteBuffer;

    private Selector selector;

    private int remoteClientNum = 0;

    public NioServer(int port) {
        try {
            initChannel(port);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("NIO服务器端异常，", e);
            System.exit(-1);
        }
    }

    public void initChannel(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        byteBuffer = ByteBuffer.allocate(size);
        logger.info("Nio Server bind port:{}", port);
    }

    public void listener() throws Exception {
        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept(); //todo 该channel和客户端创建的channel是同一个对象吗？是怎么获取到的？
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    remoteClientNum++;
                    // logger.info("NIO Server：online client num = {}", remoteClientNum);
                    write(channel, "hello client".getBytes());
                }

                if (key.isReadable()) {
                    read(key);
                }

                iterator.remove();
            }
        }
    }

    public void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            StringBuffer buffer = new StringBuffer();
            while (byteBuffer.hasRemaining()) {
                buffer.append((char) byteBuffer.get());
            }

            logger.info("服务端_收到消息：{}，时间：{}", buffer, System.currentTimeMillis());
            byteBuffer.clear();
        }

        if (count < 0) {
            socketChannel.close();
        }
    }

    public void write(SocketChannel channel, byte[] writeData) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(writeData);
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    public void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null) {
            return;
        }

        channel.configureBlocking(false);
        channel.register(selector, opRead);
    }
}
