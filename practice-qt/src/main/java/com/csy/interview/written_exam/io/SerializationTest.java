package com.csy.interview.written_exam.io;

import com.csy.interview.written_exam.io.seria_ext.People;
import com.csy.interview.written_exam.io.seria_ext.UserInfo;
import org.junit.Test;

import java.io.*;

/**
 * @author chensy
 * @date 2023/7/13
 */
public class SerializationTest {

    /**
     * 序列化_测试
     * 1）Serialization is the conversion of the state of an object into a byte stream; deserialization does the opposite
     *   （序列化是将对象转换为字节流，反序列化刚好相反）
     *
     * 2）Classes that are eligible（合适的） for serialization need to implement a special marker interface, Serializable.
     *   （符合序列化条件的类，需要实现标记接口Serializable）
     *
     * 3）
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-serialization
     */

    /**
     * 场景1：java对象序列化、反序列化
     */
    @Test
    public void test_basic() {
        UserInfo userInfo = new UserInfo("zhang", "123", 10);
        System.out.println(userInfo);

        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("userInfo.txt")); // 1) 将对象序列化
            o.writeObject(userInfo); // 2）把对象写到输出流中
            o.close();
        } catch (Exception e) {

        }

        System.out.println("---------分隔线--------");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("userInfo.txt")); // 3）反序列化对象
            UserInfo userInfo1 = (UserInfo) in.readObject(); // 4）从输入流中读取到对象
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
         * 1）UserInfo的字段pwd，使用transient修饰，就不会被序列化
         *
         *
         */
    }

    @Test
    public void test_basic2() {
        People p = new People();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        String filePath = "src/main/java/com/csy/interview/written_exam/io/seria_ext/people.out";
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(p);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        People p1;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ois = new ObjectInputStream(fis);
            p1 = (People) ois.readObject();
            System.out.println("name:" + p1.getName());
            System.out.println("age:" + p1.getAge());
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 场景n：transient字段使用
     */
}
