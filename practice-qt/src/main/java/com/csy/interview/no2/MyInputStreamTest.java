package com.csy.interview.no2;

import com.csy.interview.no2.bio_ext.MyInputStream;
import org.junit.Test;

import java.io.*;

/**
 * @author chensy
 * @date 2023/8/14
 */
public class MyInputStreamTest {

    /**
     * 流_测试
     */

    /**
     * 场景1：自定义输入流
     */
    @Test
    public void test_inputStream() throws IOException {
        int c;
        InputStream is = null;
        try {
            is = new MyInputStream(new BufferedInputStream(new FileInputStream("src/main/resources/test.txt")));
            while ((c = is.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (is != null) {
                is.close();
            }
        }

        /**
         * 输出结果：
         * AAAbbbCCCddd123
         *
         * 结果分析：
         * 自定义了输入流MyInputStream，接收底层流InputStream，重写了read方法，实现了自定义的大写转小写、小写转大写的方法
         */
    }

     /**
     * 场景2：java10中的transferTo方法使用
     * （用于把数据从InputStream直接传输到OutputStream中）
     */
    @Test
    public void test_transferTo_from_java10() throws IOException { //在jdk10以上的版本运行
//        var cl = ClassLoader.getSystemClassLoader();
//        var inputStream = cl.getResourceAsStream("ext/input2.txt"); //获取当前模块资源下的文件
//
//        var output = new File("src/main/resources/ext/output.txt"); //使用相对路径指定文件
//        if (!output.exists()) {
//            output.createNewFile();
//        }
//        try (var outputStream = new FileOutputStream(output)){
//            inputStream.transferTo(outputStream);
//        }
    }
}
