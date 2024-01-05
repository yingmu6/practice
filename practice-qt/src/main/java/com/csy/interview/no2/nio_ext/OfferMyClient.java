package com.csy.interview.no2.nio_ext;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author chensy
 * @date 2024/1/5
 */
public class OfferMyClient {

    private int size = 1024;

    private ByteBuffer byteBuffer;

    private SocketChannel socketChannel;

    public void connectServer() throws IOException {
        socketChannel = socketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        socketChannel.configureBlocking(false);
        byteBuffer = ByteBuffer.allocate(size);

    }

    private void receive() throws IOException {
        while (true) {
            byteBuffer.clear();
            int count;
            while ((count = socketChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }
                send2Server("say hi".getBytes());
                byteBuffer.clear();
            }
        }
    }

    private void send2Server(byte[] bytes) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

    public static void main(String[] args) throws IOException {
        new OfferMyClient().connectServer();
    }
}
