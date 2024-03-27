package thinking.inner_class.case5;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Callee1 implements Incrementable {

    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}
