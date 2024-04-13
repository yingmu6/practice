package thinking.concurrent.case3;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SimpleThread extends Thread {

    private int countDown = 5;

    private static int threadCount = 0;

    public SimpleThread() {
        super(Integer.toString(++threadCount));
        start();
    }

    public String toString() {
        return "#" + getName() + "(" + countDown + ")，";
    }

    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) { //编码的变体
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
