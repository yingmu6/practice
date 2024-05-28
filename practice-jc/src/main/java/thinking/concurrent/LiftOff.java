package thinking.concurrent;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class LiftOff implements Runnable {

    protected int countDown = 10;

    private static int taskCount = 0;

    private final int id = taskCount++; //final修饰的常量值，每个对象初始化后，不能再变更

    public LiftOff() {}

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + ")，";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
           // Thread.yield();
        }
    }
}
