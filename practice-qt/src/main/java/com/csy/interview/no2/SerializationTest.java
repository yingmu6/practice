package com.csy.interview.no2;

import com.csy.interview.no2.seria_ext.UserInfo;
import org.junit.Test;

import java.io.*;

/**
 * @author chensy
 * @date 2023/7/13
 */
public class SerializationTest {

    /**
     * 序列化_测试
     */

    /**
     * 场景1：java对象序列化、反序列化
     */
    @Test
    public void test_basic() {
        UserInfo userInfo = new UserInfo("zhang", "123");
        System.out.println(userInfo);

        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("userInfo.txt")); // 1) 将对象序列化
            o.writeObject(userInfo);
            o.close();
        } catch (Exception e) {

        }

        System.out.println("---------分隔线--------");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("userInfo.txt")); // 2）反序列化对象
            UserInfo userInfo1 = (UserInfo) in.readObject();
            System.out.println(userInfo1);
        } catch (Exception e) {

        }

        /**
         * 输出结果：
         * 用户信息:
         * 用户名: zhang
         *  登录时间:Fri Jul 14 23:20:05 CST 2023
         *  密码: 123
         * ---------分隔线--------
         * 用户信息:
         * 用户名: zhang
         *  登录时间:Fri Jul 14 23:20:05 CST 2023
         *  密码: NOT SET
         *
         * 结果分析：
         *
         *
         */
    }

    /**
     * 场景n：transient字段使用
     */
}
