package thinking.concurrent.case6;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class PairManager1 extends PairManager {
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}
