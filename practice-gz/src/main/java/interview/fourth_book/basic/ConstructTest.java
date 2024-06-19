package interview.fourth_book.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/5
 */
public class ConstructTest { //@MsY-Doing

    /**
     * 知识点：构造函数
     *
     * 知识点概要：
     * 1）
     *
     * 关联点学习：
     * 1）阅读&调试JVM构造函数对应native方法：了解构造函数在JVM的初始化流程（Doing）
     * 2）junit接收异常信息的写法：代替try/catch写法（Doing）
     *
     */

    /**
     * 场景1：普通方法与构造方法名称相同
     */
    @Test
    public void test_common_method() { //Done
        ConstructTest test = new ConstructTest();
        System.out.println(test.ConstructTest());

        /**
         * 输出结果：
         * test
         *
         * 结果分析：
         * 1）没有带返回值的方法ConstructTest()，是构造方法
         *    带有返回值的同名方法，是普通方法
         * 2）普通方法名称可以和构造方法名称相同，唯一区别就是返回值
         */
    }

    /**
     * 场景2：Null强转
     */
    @Test
    public void test_null_convert() { //Done
        ((ConstructTest)null).haha();

        /**
         * 输出结果：
         * haha
         *
         * 结果分析：
         * null值可以转换为任何java类型，例如（String）null也是合法的
         * 1）但是null强转后是无效对象，若操作的是静态方法，则能正常输出。因为静态方法是与类绑定的，不要借助对象访问
         * 2）若方法不是静态方法，此时操作就会报空指针异常，因为对象为Null
         */
    }

    public static void haha() {
        System.out.println("haha");
    }


    /**
     * 场景3：Null强转
     */
    @Test
    public void test_null_convert_v2() { //Done
       try {
           ((ConstructTest)null).hahaV2();
       } catch (Exception e) {
           System.out.println("接收到异常：" + e);
       }

        /**
         * 结果输出：
         * 接收到异常：java.lang.NullPointerException
         *
         * 结果分析：
         * 1）因为null强转后，对象是无效对象，即为null对象，所以访问对象中的方法，就会抛出空指针异常
         */
    }

    public void hahaV2() {
        System.out.println("hahaV2");
    }

    public ConstructTest() {}

    public String ConstructTest() {
        return "test";
    }
}
