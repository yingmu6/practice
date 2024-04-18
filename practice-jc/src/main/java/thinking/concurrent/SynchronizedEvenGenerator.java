package thinking.concurrent;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEventValue = 0;

    @Override
    public synchronized int next() {
        ++ currentEventValue;
        Thread.yield();
        ++ currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) { //同步控制EvenGenerator
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
