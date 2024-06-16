package interview.offer_come.basic.persistent;

import org.junit.Test;

import java.io.*;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class PersistentTest {

    /**
     * 序列化和反序列化
     * 知识点概要：
     *
     */

    private String filePath = "src/main/java/com/csy/interview/offer_come/basic/persistent/worker.out";
    /**
     * 场景1：序列化
     */
    @Test
    public void testBasicUse() throws Exception {

        // 序列化数据到磁盘
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Worker testObject = new Worker();
        testObject.setName("alex");
        testObject.setSalary(10);
        oos.writeObject(testObject);
        oos.flush();
        oos.close();

        /**
         * 输出结果：
         * （将对象写到输出流中，并在指定路径下产生文件）
         *
         * 结果分析：
         * 1）先构建基础输出流FileOutputStream，在构建对象输出流ObjectOutputStream
         * 2）设置到对象的属性值，然后再通过writeObject将对象写到输出流
         * 3）定义的对象，要实现Serializable接口
         *
         * 总结概要：
         *
         */
    }

    /**
     * 场景2：反序列化
     */
    @Test
    public void deserialize() throws IOException, ClassNotFoundException {

        // 反序列化磁盘数据并解析数据状态
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Worker deTest = (Worker) ois.readObject();
        System.out.println("普通成员变量：" + deTest.getName());
        System.out.println("transient修饰的变量：" + deTest.getSalary());
        System.out.println("静态变量：" + Worker.age);

        /**
         * 输出结果：
         * 普通成员变量：alex
         * transient修饰的变量：0
         * 静态变量：15
         *
         * 结果分析：
         * 1）普通的成员变量是可以反序列化的，但transient不能
         *
         * 总结概要：
         *
         */
    }
}
