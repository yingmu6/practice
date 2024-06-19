package relative.network.socket.use;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : chensy
 * Date : 2020-02-17 10:50
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 5555);
        /**
         * Opens a socket channel and connects it to a remote address
         * 打开socket通道并连接到远程的地址
         */
        SocketChannel channel = SocketChannel.open(socketAddress);

        // Adjusts this channel's blocking mode(调整通道是否阻塞)
        channel.configureBlocking(false);

        // Allocates a new byte buffer (分配一个新的byte缓冲区)
        ByteBuffer buffer = ByteBuffer.allocate(20);

        while (true) {
            // 从通道中读取内容写到buffer中
            int n = channel.read(buffer);
            System.out.println(n);
            System.out.println("receive data : " + new String(buffer.array(), 0, n));
            Thread.sleep(1000);
            buffer.flip();
        }
    }
}
