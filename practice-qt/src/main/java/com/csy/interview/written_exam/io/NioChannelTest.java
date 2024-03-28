package com.csy.interview.written_exam.io;

import com.csy.interview.written_exam.io.nio_ext.NioChannel;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/8/16
 */
public class NioChannelTest {

    /**
     * NIO_Channel测试
     *
     * 1）Buffer的主要作用就是与Channel进行交互，本质是一块可读写的内存
     *
     * 2）数据的读写都是通过Channel（通道）来实现的，Channel与传统的"流"非常类似，只不过Channel不能直接访问数据，需要通过与Buffer交互来实现
     *
     * 3）通道与流的区别：
     *    a）通道是双向的，既可以读也可以写，而大部分流是单向的
     *    b）通道可以支持异步的读写，大部分流只支持同步的读写
     *    c）通道的读写只能通过与Buffer的交互来完成
     *
     * 4）Buffer中重要属性介绍：（熟悉这个属性的关系）
     *    a）关系为：mark <= position <= limit <= capacity
     *    b）capacity：表示Buffer的容量
     *    c）position：表示下一次读（写）的位置
     *    d）limit：表示本次读（写）的极限位置
     *
     * 5）Buffer中常用的方法：
     *    a）flip()：意为"范围"，读与写模式转换，把position置为0，limit设置为当前的position值
     *    b）rewind()：意为"倒带，倒回"，可以重复读取数据，只把position置为0，limit的值不改变
     *
     * 6）在Java语言中，主要有4个常见的Channel
     *    a）FileChannel：用于读写文件
     *    b）DatagramChannel：用于对UDP的数据进行读写
     *    c）SocketChannel：用于对TCP数据进行读写，一般用作客户端实现
     *    d）ServerSocketChannel：用于监听TCP的连接请求，然后针对每个请求会创建一个ServerSocketChannel，一般用作服务端实现
     */

    /**
     * 场景1：Channel与Buffer基本使用
     */
    @Test
    public void test_channel_buffer_basic() {
        NioChannel nioChannel = new NioChannel();
        String content = "hello world";
        nioChannel.writeFile(content);
        nioChannel.readFile(content.length());

        /**
         * 输出的结果：
         * 读到的字节数：11
         * hello world
         *
         * 结果分析：
         * 1）ByteBuffer底层维护了字节数组、还有对应的游标position、limit、mark等，通过游标position添加或获取字节
         * 2）ByteBuffer中的flip，表面意思是"翻转"，本质是将position置为0，其它游标置为初始值，通过flip来实现读、写模式的切换
         */
    }
}
