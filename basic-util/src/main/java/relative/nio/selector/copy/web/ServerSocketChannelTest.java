package relative.nio.selector.copy.web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * ServerSocketChannel： socket的服务端
 * @author : chensy
 * Date : 2020-03-02 00:35
 */
public class ServerSocketChannelTest {
    public static void main(String[] args) {
        try (
                // 打开通道
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                // 打开选择器
                Selector selector = Selector.open();
        ) {
            // 把通道绑定到指定的IP上
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8083));
            // 将通道配置为非阻塞
            serverSocketChannel.configureBlocking(false);
            System.out.println("server 启动...");
            // 将通道注册到选择器上
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select() > 0) {
                // 选择的键的集合
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while(keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                        // 通过选择键获取到通道
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // 从通道中读取数据到buffer中
                        int readBytes = socketChannel.read(buffer);
                        if (readBytes > 0) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String body = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println("server 收到：" + body);
                        }
                    } else if(key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

