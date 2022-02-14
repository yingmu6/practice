package relative.network.socket.use.test1;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author : chensy
 * Date : 2020/8/23 上午11:49
 */
public class Server1 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
        /**
         * accept() 阻塞的，直到获取到链接
         */
        while(true) { // 循环接收客户端请求
            ServerSocketChannel serverSocketChannel = serverSocket.getChannel();

            Socket socket = serverSocket.accept();
//            InetAddress address = socket.getInetAddress();
            ByteBuffer byteBuffer = ByteBuffer.allocate(50);

            /**
             * todo 0823 此处socketChannel为哈会返回空？
             */
            SocketChannel socketChannel = socket.getChannel();
            socketChannel.read(byteBuffer);

//            serverSocketChannel.(byteBuffer);
//            System.out.println("客户端地址：" + address.getHostAddress());
            System.out.println(new String(byteBuffer.array()));

        }

    }
}
