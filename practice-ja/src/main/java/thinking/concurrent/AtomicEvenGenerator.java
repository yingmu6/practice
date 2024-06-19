package thinking.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class AtomicEvenGenerator extends IntGenerator {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
