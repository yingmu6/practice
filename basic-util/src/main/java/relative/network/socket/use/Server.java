package relative.network.socket.use;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author : chensy
 * Date : 2020-02-17 10:49
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建socket地址
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 5555);

        // Opens a server-socket channel(打开服务端socket的通道)
        ServerSocketChannel server = ServerSocketChannel.open();

        //Binds the ServerSocket to a specific address（绑定ServerSocket到指定的地址）
        server.socket().bind(socketAddress);

        SocketChannel channel = null;
        // Accepts a connection made to this channel's socket（接收连接作为通道的socket）
        while ((channel = server.accept()) != null) {

            // Returns an output stream for this socket（从socket返回一个输出流）
            OutputStream out = channel.socket().getOutputStream();
            while (true) {
                out.write("hello world !!".getBytes());
                out.flush();
            }
        }
    }
}
