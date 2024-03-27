package thinking.inner_class.case1;

import thinking.inner_class.case1.Implementation1;
import thinking.inner_class.case1.Implementation2;
import thinking.inner_class.case1.Service;
import thinking.inner_class.case1.ServiceFactory;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Factories {

    public static void serviceConsumer(ServiceFactory fact) {
       Service s = fact.getService();
       s.method1();
       s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implementation1.factory);
        serviceConsumer(Implementation2.factory);

        /**
         * （场景：访问工厂方法）
         *
         * 输出结果：
         * Implementation1 method1
         * Implementation2 method2
         * Implementation1 method1
         * Implementation2 method2
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
