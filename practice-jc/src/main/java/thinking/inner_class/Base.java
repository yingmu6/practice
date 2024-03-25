package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public abstract class Base {

    public Base(int i) {
        System.out.println("Base constructor，i =" + i);
    }

    public abstract void fn();
}
