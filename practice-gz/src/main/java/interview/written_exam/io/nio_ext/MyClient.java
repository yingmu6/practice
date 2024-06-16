package interview.written_exam.io.nio_ext;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author chensy
 * @date 2023/8/16
 */
public class MyClient {

    public void startClient() {
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open();
            channel.connect(new InetSocketAddress("127.0.0.1", 8800));

            ByteBuffer writeBuf = ByteBuffer.allocate(1024);
            ByteBuffer readBuf = ByteBuffer.allocate(1024);

            writeBuf.put("Hello Server".getBytes());
            writeBuf.flip();

//            while (true) { //此处打开，会一直向服务端发请求，CPU会暴增
                writeBuf.rewind();
                channel.write(writeBuf);
                readBuf.clear();
                channel.read(readBuf);
                System.out.println("客户端接收到的数据：" + new String(readBuf.array()));
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
