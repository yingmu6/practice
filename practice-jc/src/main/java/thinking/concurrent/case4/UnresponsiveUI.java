package thinking.concurrent.case4;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class UnresponsiveUI {

    private volatile double d = 1;

    public UnresponsiveUI() throws Exception {
        while (d > 0)
            d = d + (Math.PI + Math.E) / d;
        System.in.read();
    }
}
