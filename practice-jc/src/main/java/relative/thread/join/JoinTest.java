package relative.thread.join;

/**
 * Thread join测试
 * https://juejin.im/post/5b3054c66fb9a00e4d53ef75
 * 线程的合并的含义就是 将几个并行线程的线程合并为一个单线程执行，应用场景是
 * 当一个线程必须等待另一个线程执行完毕才能执行时，Thread类提供了join方法来完成这个功能，注意，它不是静态方法
 *
 * @author : chensy
 * Date : 2020-02-17 00:08
 */
public class JoinTest {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            SubThread1 thread1 = new SubThread1();
            SubThread2 thread2 = new SubThread2();
            thread2.start();
            thread1.start();

            try {
                thread2.join(); //多个线程合并成一个线程执行，join加入的线程顺序执行。主程序先等待thread2执行
                //thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程执行完毕！");
        }
    }
}
