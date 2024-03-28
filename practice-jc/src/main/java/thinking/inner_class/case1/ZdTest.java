package thinking.inner_class.case1;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ZdTest {

    /**
     * 场景1：访问工厂方法
     */
    @Test
    public void basicUse() {
        Factories.serviceConsumer(Implementation1.factory);
        Factories.serviceConsumer(Implementation2.factory);

        /**
         *
         * 输出结果：
         * Implementation1 method1
         * Implementation1 method2
         * Implementation2 method1
         * Implementation2 method2
         *
         * 结果分析：
         * 1）Implementation1、Implementation2都实现了Service接口，并维护着静态成员factory（通过匿名类方式返回实例）
         * 2）Factories根据传入的不同工厂实例，获取对应的Service实例，并执行对应的方法method1、method2
         *
         * 总结概括：
         */
    }
}
