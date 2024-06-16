package interview.written_exam.thread.thread_ext;

/**
 * @author chensy
 * @date 2023/9/12
 */
public class ThreadException extends Thread {

    public void run() {
        int i = 1 / 0;
    }

    public void simulateCleanup() { //模拟清除
        System.out.println("Simulate clean up");
    }
}
