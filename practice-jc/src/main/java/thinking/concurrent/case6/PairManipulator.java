package thinking.concurrent.case6;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class PairManipulator implements Runnable {

    private PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    public String toString() {
        return "Pair：" + pm.getPair() +
                " checkCounter = " + pm.checkCounter.get();
    }
}
