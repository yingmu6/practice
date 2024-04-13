package thinking.concurrent.case5;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++ currentEvenValue;
        ++ currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) { //共享受限资源
        EvenChecker.test(new EvenGenerator());
    }
}
