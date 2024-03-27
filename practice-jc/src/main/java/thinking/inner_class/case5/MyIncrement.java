package thinking.inner_class.case5;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MyIncrement {

    public void increment() {
        System.out.println("Other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment();
    }
}
