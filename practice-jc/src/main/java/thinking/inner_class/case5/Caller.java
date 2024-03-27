package thinking.inner_class.case5;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Caller {

    private Incrementable callbackReference;

    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}
