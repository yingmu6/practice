package relative.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 服务端: 基于DatagramChannel  https://wiki.jikexueyuan.com/project/java-nio/datagramchannel.html
 * Java NIO 中的 DatagramChannel 是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。
 * @author : chensy
 * Date : 2020-03-02 00:14
 */
public class Server {
    public static void main(String[] args) throws Exception {
        // 打开UDP的通道，并将通道绑定到自定端口
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        // 此处UDP通道是使用receive()，而不是read()
        channel.receive(byteBuffer);
        byteBuffer.flip();
        byte [] arr = byteBuffer.array();
        for (int i = 0; i < arr.length; i++) {
            System.out.println("接收到的数据:" + arr[i]);
        }

    }

    /**
     * 当尝试在尚未连接的套接字通道上调用I / O操作时抛出未检查的异常。  java.nio.channels.NotYetConnectedException
     */
}
