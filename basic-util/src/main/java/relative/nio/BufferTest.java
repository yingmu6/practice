package relative.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer (A container for data of a specific primitive type 指定类型数据的容器，是抽象类)
 * Buffer缓冲区：本质上是一块既能写入又能从中读取的内存块
 * http://ifeve.com/buffers/
 * @author : chensy
 * Date : 2020-03-01 18:14
 */
public class BufferTest {
    public static void main(String[] args) throws Exception {
        // createByPut();
        createByChannel();
    }

    /**
     * position表示当前的位置，从0开始，每次读取或写入后，position都会向前移动到下一个位置，比如一开始position=0，当加一个元素后就会移到第二个位置即position=1
     *
     */
    public static void createByPut() {
        // 分配指定容量的buffer缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(3);
        // 使用put向buffer写入数据
        buffer.put((byte) 3);
        buffer.put((byte) 5);
        buffer.put((byte) 6);
       // buffer.put((byte) 7); 若超出容量capacity，会报缓存溢出异常  java.nio.BufferOverflowException

        // 翻转：是将position赋值给limit，并把position置为0，若不翻转flip() 可以获取指定位置值，get(index) 但是直接get()会取当前位置position的值，会报异常
        buffer.flip();
        System.out.println(buffer.get(0) + ";" + buffer.get(1) + ";" + buffer.get(2));
        System.out.println("获取" + buffer.get()); // 若不用flip(), 会报出java.nio.BufferUnderflowException
        System.out.println("获取" + buffer.get());
        System.out.println("获取" + buffer.get());

    }

    /**
     * 从通道读取内容到buffer中
     */
    public static void createByChannel() throws Exception {
        // 获取文件也可以通过new File(path)
//        ClassLoader loader = BufferTest.class.getClassLoader();
//        URL url = loader.getResource("/Users/chenshengyong/selfPro/dubbo-util/src/main/java/relative/nio/nio_data.txt");
//        if (url == null) {
//            return;
//        }

        File file = new File("/Users/chenshengyong/selfPro/dubbo-util/src/main/java/relative/nio/nio_data.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        int byteNum = fc.read(byteBuffer);
        if (byteNum != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println("读取的内容：" + byteBuffer.get());
            }
        }
        fis.close();

    }
}
