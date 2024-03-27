package thinking.inner_class.case1;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Implementation1 implements Service {

    private Implementation1() {}

    @Override
    public void method1() {
        System.out.println("Implementation1 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation2 method2");
    }

    public static ServiceFactory factory =
            new ServiceFactory() {
                @Override
                public Service getService() {
                    return new Implementation1();
                }
            };
}
