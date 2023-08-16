package com.csy.interview.no2.nio_ext;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author chensy
 * @date 2023/8/16
 */
public class NioChannel {

    public void writeFile(String content) {
        RandomAccessFile raf = null;
        FileChannel fc = null;

        try {
            raf = new RandomAccessFile("src/main/resources/input.txt", "rw");
            fc = raf.getChannel();
            ByteBuffer writeBuf = ByteBuffer.allocate(content.length());
            writeBuf.put(content.getBytes());
            writeBuf.flip(); //将Buffer变为读模式
            fc.write(writeBuf); //把ByteBuffer的数据写到Channel中
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException e) {
                }
            }

            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void readFile(int size) {
        RandomAccessFile raf = null;
        FileChannel fc = null;
        try {
            raf = new RandomAccessFile("src/main/resources/input.txt", "rw");
            fc = raf.getChannel();
            ByteBuffer readBuf = ByteBuffer.allocate(size); //创建用来读取数据的ByteBuffer
            int bytesRead = fc.read(readBuf); //从Channel中把数据读取到ByteBuffer中
            while (bytesRead != -1) {
                System.out.println("读到的字节数：" + bytesRead);
                readBuf.flip(); //在执行fc.read时，position已经改变了，所以此处需要使用flip将position翻转下，即置为0以后，才能读取到内容
                while (readBuf.hasRemaining()) {
                    System.out.print((char) readBuf.get()); //读取数据，即取position对应的元素
                }
                readBuf.clear(); //清空缓冲区
                bytesRead = fc.read(readBuf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException e) {

                }
            }

            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
