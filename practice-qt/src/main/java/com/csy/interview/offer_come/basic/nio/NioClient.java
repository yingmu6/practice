package com.csy.interview.offer_come.basic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author chensy
 * @date 2024/1/5
 */
public class NioClient {

    Logger logger = LoggerFactory.getLogger(NioClient.class);

    private int size = 1024;

    private ByteBuffer byteBuffer;

    private SocketChannel socketChannel;

    public void connectServer() throws IOException {
        socketChannel = socketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        socketChannel.configureBlocking(false);
        byteBuffer = ByteBuffer.allocate(size);

    }

    public void receive() throws IOException {
        while (true) {
            byteBuffer.clear();
            while (socketChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                StringBuffer buffer = new StringBuffer();
                while (byteBuffer.hasRemaining()) {
                    buffer.append((char) byteBuffer.get());
                }

                logger.info("客户端_收到消息：{}，时间：{}", buffer, System.currentTimeMillis());
                byteBuffer.clear();
            }
        }
    }

    public void send(byte[] bytes) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

}
