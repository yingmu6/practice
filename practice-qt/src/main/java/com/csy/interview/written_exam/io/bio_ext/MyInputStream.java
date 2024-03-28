package com.csy.interview.written_exam.io.bio_ext;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
  * 自定义输入流
  * @author chensy
  * @date 2023/8/14
 */
public class MyInputStream extends FilterInputStream { //FilterInputStream封装了底层流InputStream，可实现附加功能

    public MyInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int c = 0;
        if ((c = super.read()) != -1) {
            if (Character.isLowerCase((char)c)) { //小写字母转换为大写字母
                return  Character.toUpperCase((char) c);
            } else if (Character.isUpperCase((char)c)) { //将大写字母转换为小写字母
                return Character.toLowerCase((char) c); //toLowerCase返回的是char类型，char类型可以转换为int类型
            } else {
                return c;
            }
        } else {
            return -1;
        }
    }
}
