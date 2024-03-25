package com.csy.interview.offer_come.basic.persistent;

import org.junit.Test;

import java.io.*;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class PersistentTest {

    @Test
    public void testBasicUse() throws Exception {
        // 序列化数据到磁盘
        FileOutputStream fos = new FileOutputStream("worker.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Worker testObject = new Worker();
        testObject.setName("alex");
        oos.writeObject(testObject);
        oos.flush();
        oos.close();

        // 反序列化磁盘数据并解析数据状态
        FileInputStream fis = new FileInputStream("work.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Worker deTest = (Worker) ois.readObject();
        System.out.println(deTest.getName());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 总结概要：
         *
         * 问题点答疑：
         * 1）抛出异常：Exception in thread "main" java.io.FileNotFoundException: work.out (No such file or directory)
         * 2）static成员为什么不能被序列化？
         */
    }
}
