package thinking.io_relative;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
     *
     *
     * 关联点学习：
     * 1）文件读写的native方法核心逻辑了解（Doing）
     * 2）ByteBuffer的实现类HeapByteBuffer与DirectorByteBuffer了解（Doing）
     * 3）堆Heap的数据结构对应代码实践（Doing）
     *
     *
     * 问题点答疑：
     * 1）什么叫零拷贝？ByteBuffer中的DirectorBuffer是指直接对外内存吗？是直接调用操作系统的接口处理内存吗？
     * 2）HeapByteBuffer的是用数组来存储的吗？为什么称之为堆Heap，堆的特性是什么？
     *
     *
     */
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException { //Doing
        FileChannel fc =
                new FileOutputStream("data2.txt").getChannel(); //返回与文件输出流关联的文件通道
        fc.write(ByteBuffer.wrap("Some text".getBytes())); //将ByteBuffer中的字节序列写到Channel中
        fc.close();//关闭通道（FileChannelImpl有在implCloseChannel()实现通道关闭的逻辑）
        fc = new FileInputStream("data2.txt").getChannel(); //获取输入流对应的通道Channel
        ByteBuffer buff = ByteBuffer.allocate(BSIZE); //分配指定容量的ByteBuffer
        fc.read(buff); //从通道中读取字节序列写到ByteBuffer中（Channel的readable = true，即可读）
        buff.flip(); //翻转，即将position赋值该limit，且将position值置为0
        System.out.println(buff.asCharBuffer());
        buff.rewind(); //倒带，将position置为0
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
         * 1）AbstractInterruptibleChannel中维护着通道关闭的标识close，初始时为true，调用close()方法为false，还提供implCloseChannel()方法，可重写实现关闭逻辑
         * 2）A container for data of a specific primitive type.（Byte 是一个指定基本类型的容器）
         * 3）FileChanelImpl中维护着writeable（是否可写）、readable（是否可读），在写或读的时候需要判断这两个属性值
         * 4）flip()功能：将limit = position，position = 0，mark = -1，因为buffer就一个游标position，所以读写切换时，需要改变position的值
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
    public void test_flip_rewind() {

        // 创建ByteBuffer
        ByteBuffer buffer = ByteBuffer.wrap("hello ByteBuffer".getBytes());
        System.out.println(buffer);

        // 把ByteBuffer内容写到Channel
//        FileChannel channel = new FileOutputStream();


        /**
         * 输出结果：
         *
         *
         * 结果分析：
         *
         *
         *
         */
    }

    //测试文件路径
    @Test
    public void test_file_path() throws IOException { //Doing

        String filePath = BufferToText.class.getResource("test.txt").getPath();
        String filePath2 = BufferToText.class.getResource("test.txt").getFile();

        System.out.println(filePath);
        System.out.println(filePath2);

//        String filePath = "test.txt";
        FileChannel channel = new FileOutputStream(filePath).getChannel();
        channel.write(ByteBuffer.wrap("haha".getBytes()));
        channel.close();

        /**
         * 结果分析：
         * 1）若filePath = "test.txt"，在practice-ja模块下生成test.txt文件
         *
         *
         * 问题点答疑：
         * 1）ClassLoader中的getResource(String name)方法的用途是什么？
         * 2）AppClassLoader、ExtClassLoader、BootstrapLoader的继承关系是怎样的？为什么叫作双亲加载机制？
         * 3）怎样获取当前类所在包的相对路径和绝对路径？
         */
    }
}
