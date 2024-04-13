package thinking.concurrent.case3;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class ThreadVariations {

    public static void main(String[] args) { //使用内部类将线程代码隐藏在类中将会很有用
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}
