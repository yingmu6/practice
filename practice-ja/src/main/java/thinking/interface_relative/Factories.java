package thinking.interface_relative;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Factories {

    /**
     * 知识点（9.9）：接口与工厂
     */
    interface Service {
        void method1();
        void method2();
    }

    interface ServiceFactory {
        Service getService();
    }

    static class Implementation1 implements Service {
        Implementation1() {}
        public void method1() {
            print("Implementation1 method1");
        }
        public void method2() {
            print("Implementation1 method2");
        }
    }

    static class Implementation1Factory implements ServiceFactory {
        public Service getService() {
            return new Implementation1();
        }
    }

    static class Implementation2 implements Service {
        Implementation2() {}
        public void method1() {
            print("Implementation2 method1");
        }
        public void method2() {
            print("Implementation2 method2");
        }
    }

    static class Implementation2Factory implements ServiceFactory {
        public Service getService() {
            return new Implementation2();
        }
    }

    public static void serviceConsumer(ServiceFactory fact) {
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(new Implementation1Factory());
        serviceConsumer(new Implementation2Factory());
    }
}
