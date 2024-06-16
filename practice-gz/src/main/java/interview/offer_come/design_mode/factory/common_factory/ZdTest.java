package interview.offer_come.design_mode.factory.common_factory;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class ZdTest {

    /**
     * 知识点：普通工厂模式：
     *
     * 总结概括：
     *
     * 参考信息：
     */

    @Test
    public void basicUse() {
        Factory factory = new Factory();
        Phone huawei = factory.createPhone("HuaWei");
        Phone iphone = factory.createPhone("Apple");
        System.out.println(huawei.brand());
        System.out.println(iphone.brand());

        /**
         * 输出结果：
         * this is a huawei phone
         * this is a Apple phone
         *
         * 结果分析：
         * 1）Factory中的方法createPhone会根据传入的品牌类型选择new对应的实例
         *
         * 结果概括：
         * 1）工厂模式是用于创造对象实例的。普通工厂创建的对象，属于同一种类型，但可产生不同的对象实例
         * 2）普通的工厂模式，就是工厂是具体工厂，只能产生同一类型的对象实例
         */
    }
}
