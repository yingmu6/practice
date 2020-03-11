package relative.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通道Channel：用来处理Buffer，和Buffer进行数据交互
 * 从通道中读取数据read写到缓冲区buffer，把缓冲区的数据写到write()通道
 * http://ifeve.com/channels/
 * @author : chensy
 * Date : 2020-03-01 22:26
 */
public class ChannelTest {
    public static void main(String[] args) throws Exception {
        basicUse();
    }

    public static void basicUse() throws Exception {
        File file = new File("/Users/chenshengyong/selfPro/dubbo-util/src/main/java/relative/nio/nio_data.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        // channel.close();
        System.out.println("通道是否打开：" + channel.isOpen());

        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        int num = channel.read(byteBuffer); //若通道关闭了，继续调用read()会报异常，java.nio.channels.ClosedChannelException
        System.out.println("从通道中:" + num);
    }
}
