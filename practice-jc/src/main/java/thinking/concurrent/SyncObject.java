package thinking.concurrent;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SyncObject { //@JaY-Doing

    /**
     * 知识点：synchronized
     *
     * 知识点概括：
     * 1）
     */

    static class DualSynch {
        private Object syncObject = new Object();
        public synchronized void fn() {
            for (int i = 0; i < 5; i++) {
                print("f()");
                Thread.yield();
            }
        }
        public void g() {
            synchronized (syncObject) {
                for (int i = 0; i < 5; i++) {
                    print("g()");
                    Thread.yield();
                }
            }
        }

        public synchronized void fn2() {
            for (int i = 0; i < 5; i++) {
                print("f2()");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) { //在其他对象上加锁
        final DualSynch ds = new DualSynch();
        new Thread() {
            public void run() {
                ds.fn();
            }
        }.start();
        ds.g();
        ds.fn2();

        /**
         * 输出结果：（每次运行结果会变）
         * g()
         * f()
         * ....
         * f2()
         *
         * 结果分析：
         * 1）g()与f()是并发执行：
         *    1.1）f()作用在普通方法上，是对当前调用对象进行加锁
         *    1.2）g()作用在syncObject对象上，是对该对象加锁
         *    所以两者之前加锁的对象不一样，就不会同步阻塞
         *
         * 2）f()与f2()是顺序执行：
         *    两个方法都是作用在普通方法上，都是对当前调用对象进行加锁，调用的对象时同一个，所以会进行同步阻塞。
         */
    }
}
