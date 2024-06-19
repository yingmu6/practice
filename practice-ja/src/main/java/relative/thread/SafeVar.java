package relative.thread;

/**
 * @author chensy
 * @date 2019-05-29 14:33
 */
public class SafeVar implements Runnable {
    public static int var = 0;
    @Override
    public void run() {
        calculate();
    }

    public synchronized void calculate() {
        while (var < 10) {
            var = var + 1;
            System.out.println("V1中线程名：" + Thread.currentThread().getName() + ", 变量值：" + var);
        }
    }
}
