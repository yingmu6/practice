package interview.written_exam.io.nio_ext;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author chensy
 * @date 2023/8/16
 */
public class MyServer { //Selector服务端

    public void startServer() {

        Selector selector = null;

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open(); //创建服务端的SocketChannel
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8800));
            ssc.configureBlocking(false); //设置为非阻塞模型

            selector = Selector.open(); //创建选择器
            ssc.register(selector, SelectionKey.OP_ACCEPT); //注册Channel，同时注册感兴趣的事件

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(1024);
            writeBuff.put("Hello client".getBytes());
            writeBuff.flip();

            while (true) {
                int readNum = selector.select(); //此处会阻塞等待（在至少一个通道被选择时，才会有返回）
                if (readNum == 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys(); //获取就绪的keys
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) { //遍历就绪的通道
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) { //测试是否可连接
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ); //可连接以后，对读操作感兴趣
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);

                        readBuff.flip();
                        System.out.println("服务接收到的数据：" + new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_WRITE); //读完数据后，对写感兴趣
                    } else if (key.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    it.remove(); //处理完事件后从就绪的keys种删除
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                }
            }
        }

        /**
         * 输出结果：
         * 服务接收到的数据：Hello Server                         ....
         *
         * 结果分析：
         *
         * 问题点：
         * 1）字符串末尾输出一长串"   "，是从哪里来的？
         */
    }
}
