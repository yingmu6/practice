package thinking.inner_class.case5;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Callee2 extends MyIncrement {

    private int i = 0;

    public void increment() {
        super.increment();
        i++;
        System.out.println(i);
    }

    private class Closure implements Incrementable {

        private int i = 0;

        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference() {
        return new Closure();
    }
}
