package relative.thread;

/**
 * 多线程通过final访问主线程局部变量
 * https://xtuhcy.iteye.com/blog/2170295
 *
 * 有关线程安全的探讨--final、static、单例、线程安全
 * https://www.cnblogs.com/bellkosmos/p/5340711.html
 *
 * @author chensy
 * @date 2019-05-29 14:36
 */
public class ThreadTest {
    public static void main(String[] args) {
//        single_thread();
        multi_thread();
    }

    // 单线程使用
    public static void single_thread() {
        SafeVar safeVar = new SafeVar();
        Thread thread = new Thread(safeVar);
        thread.start();
    }

    // 多线程处理
    public static void multi_thread() {
       SafeVar safeVar1 = new SafeVar();
       SafeVar safeVar2 = new SafeVar();

       // Thread的run方法是启动线程后，线程获取到资源后，回调run方法的，不用主动去掉，主动去掉用的，只是一个普通方法，并没有发挥线程作用
       // safeVar1.run();
       // safeVar2.run();

       // 多线程时，对于共享变量，有现成安全问题
       Thread thread1 = new Thread(safeVar1);
       Thread thread2 = new Thread(safeVar2);
       thread1.start(); // todo @csy 此处为啥SafeVar的run方法加了synchronized修饰，和单线程的数据结果不一样？
       thread2.start();
    }
}
