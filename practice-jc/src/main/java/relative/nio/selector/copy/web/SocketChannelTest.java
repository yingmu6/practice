package relative.nio.selector.copy.web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * SocketChannel：Socket的客户端
 * @author : chensy
 * Date : 2020-03-02 00:34
 */
public class SocketChannelTest {
    public static void main(String[] args) {
        try (
                SocketChannel socketChannel = SocketChannel.open();
        ) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8083));
            System.out.println("client 启动...");

            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            buffer.put("hi, 这是client".getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

