package relative.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 客户端
 * @author : chensy
 * Date : 2020-03-02 00:14
 */
public class Client {
    public static void main(String[] args) throws Exception {
        DatagramChannel client = DatagramChannel.open();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 7);
        byteBuffer.put((byte) 9);

        // 需要调用flip() 进行翻转，不然服务端接口到的数据都是0
        byteBuffer.flip();
        // 发送数据到指定地址的服务端
        client.send(byteBuffer, new InetSocketAddress("localhost", 9999));
    }

    /**
     * Exception in thread "main" java.net.NoRouteToHostException: No route to host  没有路由到host
     */
}
