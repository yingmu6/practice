package relative.network.socket.use.test1;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : chensy
 * Date : 2020/8/23 上午11:49
 */
public class Client1 {
    public static void main(String[] args) throws Exception {
        /**
         * 与指定host、端口的服务端建立连接
         */
//        Socket socket = new Socket("localhost", 6666);

        /**
         * 传输数据
         */
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 6666));
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        byteBuffer.put("hello".getBytes());
        socketChannel.write(byteBuffer);

    }
}
