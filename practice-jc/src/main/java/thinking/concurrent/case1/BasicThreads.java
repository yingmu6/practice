package thinking.concurrent.case1;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class BasicThreads {
    public static void main(String[] args) { //继承Thread类
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
