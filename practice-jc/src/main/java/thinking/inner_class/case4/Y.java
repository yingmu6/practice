package thinking.inner_class.case4;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Y implements A {
    B makeB() {
        return new B(){};
    }
}
