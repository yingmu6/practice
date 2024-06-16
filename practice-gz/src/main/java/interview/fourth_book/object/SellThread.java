package interview.fourth_book.object;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class SellThread implements Runnable {

    private int i = 20;

    String a = "now ok!";

    @Override
    public void run() {
        while (true) {
            synchronized (a) {
                if (i > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {

                    }
                    System.out.println(Thread.currentThread().getName() + " sell " + i--);
                }
            }
        }
    }

    public static void main(String[] args) {
        SellThread sell = new SellThread();
        Thread sell1 = new Thread(sell, "sellman1");
        Thread sell2 = new Thread(sell, "sellman2");
        Thread sell3 = new Thread(sell, "sellman3");
        sell1.start();
        sell2.start();
        sell3.start();
    }
}
