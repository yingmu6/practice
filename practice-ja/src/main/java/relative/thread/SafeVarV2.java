package relative.thread;

/**
 * @author chensy
 */
public class SafeVarV2 extends Thread {
    public static int var = 0;

    @Override
    public void run() {
        calculate();
    }

    public synchronized void calculate() {
        while (var < 10) {
            var = var + 1;
            System.out.println("V2中线程名：" + Thread.currentThread().getName() + ", 变量值：" + var);
        }
    }
}
