package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Factories {

    interface Service {
        void method1();
        void method2();
    }

    interface ServiceFactory {
        Service getService();
    }

    static class Implementation1 implements Service {
        private Implementation1() {}
        @Override
        public void method1() {
            System.out.println("Implementation1 method1");
        }

        @Override
        public void method2() {
            System.out.println("Implementation1 method2");
        }
        public static ServiceFactory factory =
                new ServiceFactory() { //匿名类
                    @Override
                    public Service getService() {
                        return new Implementation1();
                    }
                };
    }

    static class Implementation2 implements Service {
        private Implementation2() {}
        @Override
        public void method1() {
            System.out.println("Implementation2 method1");
        }
        @Override
        public void method2() {
            System.out.println("Implementation2 method2");
        }
        public static ServiceFactory factory =
                new ServiceFactory() {
                    @Override
                    public Service getService() {
                        return new Implementation2();
                    }
                };
    }

    public static void serviceConsumer(ServiceFactory fact) {
       Service s = fact.getService();
       s.method1();
       s.method2();
    }

    /**
     * 访问工厂方法
     */
    public static void main(String[] args) {
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
