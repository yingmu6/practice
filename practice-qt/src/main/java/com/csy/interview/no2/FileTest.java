package com.csy.interview.no2;

import org.junit.Test;

import java.io.File;

/**
 * @author chensy
 * @date 2023/8/14
 */
public class FileTest {

    /**
     * 文件_测试
     */
    @Test
    public void test_file_directory() {
        File file = new File("/Users/shengyong.chen/Desktop/Cloud_File");
        if (!file.exists()) {
            System.out.println("目录是空的!");
            return;
        }
        File[] files = file.listFiles(); //查看目录下的目录和文件（只是指定目录下的，没有把子目录下遍历处理）
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                System.out.println("目录名：" + files[i].getName());
            } else {
                System.out.println("文件名：" + files[i].getName());
            }
        }
    }
}
