package thinking.concurrent.case1;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class MoreBasicThreads {
    public static void main(String[] args) { //使用更多的线程执行任务
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();;
            System.out.println("Waiting for LiftOff");
        }
    }
}
