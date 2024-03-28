package com.csy.interview.fourth_book.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/5
 */
public class ConstructTest {

    /**
     * 构造函数_测试
     */

    /**
     * 场景1：普通方法与构造方法名称相同
     *     （普通方法名称可以和构造方法名称相同，唯一区别就是带有返回值）
     */
    @Test
    public void test_common_method() {
        ConstructTest test = new ConstructTest();
        System.out.println(test.ConstructTest());
    }

    /**
     * 场景2：Null强转
     *
     * 输出结果：
     * haha
     *
     * 结果分析：
     * null值可以转换为任何java类型，例如（String）null也是合法的
     * 1）但是null强转后是无效对象，若操作的是静态方法，则能正常输出。因为静态方法是与类绑定的，不要借助对象访问
     * 2）若方法不是静态方法，此时操作就会报空指针异常，因为对象为Null
     */
    @Test
    public void test_null_convert() {
        ((ConstructTest)null).haha();
    }

    public static void haha() {
        System.out.println("haha");
    }


    /**
     * 场景3：Null强转
     *
     * 结果输出：
     * 会抛出空指针异常
     *
     * 结果分析：
     * 因为null强转后，对象是无效对象，即为null对象，所以访问对象中的方法，就会抛出空指针异常
     */
    @Test
    public void test_null_convert_v2() {
        ((ConstructTest)null).hahaV2();
    }

    public void hahaV2() {
        System.out.println("hahaV2");
    }

    public ConstructTest() {}

    public String ConstructTest() {
        return "test";
    }
}
