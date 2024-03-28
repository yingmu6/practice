package thinking.inner_class.case1;

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
}
