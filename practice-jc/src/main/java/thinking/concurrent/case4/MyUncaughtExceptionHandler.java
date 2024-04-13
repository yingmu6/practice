package thinking.concurrent.case4;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}
