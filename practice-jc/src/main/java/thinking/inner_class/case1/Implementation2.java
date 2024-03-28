package thinking.inner_class.case1;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Implementation2 implements Service {

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
