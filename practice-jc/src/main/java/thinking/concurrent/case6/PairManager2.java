package thinking.concurrent.case6;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class PairManager2 extends PairManager {
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}
