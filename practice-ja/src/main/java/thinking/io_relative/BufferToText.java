package thinking.io_relative;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author orange
 * @date 2024/6/7
 */
public class BufferToText { //@TkY-Doing

    /**
     * 知识点（18.10.1）：转换数据
     *
     * 知识点概括：
     *
     * 关联点学习：
     * 1）文件读写的native方法核心逻辑了解（Doing）
     * 2）ByteBuffer的实现类HeapBuffer与DirectorBuffer了解（Doing）
     *
     */
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fc =
                new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes())); //将ByteBuffer中的字节序列写到Channel中
        fc.close();//关闭通道
        fc = new FileInputStream("data2.txt").getChannel(); //获取输入流对应的通道Channel
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff); //从通道中读取字节序列写到ByteBuffer中（Channel的readable = true，即可读）
        buff.flip();
        System.out.println(buff.asCharBuffer());
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoding using " + encoding + "："
                + Charset.forName(encoding).decode(buff));
        fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
        fc = new FileOutputStream("data2.txt").getChannel(); //之前写为FileInputStream，报出不可写异常
        buff = ByteBuffer.allocate(24);
        buff.asCharBuffer().put("Some text");
        fc.write(buff);
        fc.close();
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());

        /**
         * 输出结果：
         * 卯浥⁴數
         * Decoding using UTF-8：Some text
         * Some text
         * Some text   
         *
         * 结果分析：
         *
         *
         * 问题点答疑：
         * 1）46行处的fc.write(buff) 代码抛出Exception in thread "main" java.nio.channels.NonWritableChannelException
         *    解答：因为FileChannel fc = new FileInputStream(...)，是文件输入流，只能写不能读，改为FileOutputStream即可
         *
         * 2）FileChannel的可读readable和可写writeable是怎样切换的？
         *   解答：FileChannel看指向的对象是哪个，若是FileOutputStream文件输出流，则是可写的 writeable = true
         *        若指向的是FileInputStream文件输入流，则是可读的 readable = true
         *
         */
    }

    /**
     * 场景：ByteBuffer的flip和rewind测试
     */
    @Test
    public void test_flip_rewind() { //Doing_@pause-06/19

    }
}
