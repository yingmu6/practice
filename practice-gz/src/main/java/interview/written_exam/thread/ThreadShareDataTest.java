package interview.written_exam.thread;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/1/28
 */
public class ThreadShareDataTest {

    /**
     * 多线程共享数据
     */
    private int j = 0;

    public synchronized void add() {
        j++;
        System.out.println("线程" + Thread.currentThread().getName() + "j为：" + j);
    }

    public synchronized void dec() {
        j--;
        System.out.println("线程" + Thread.currentThread().getName() + "j为：" + j);
    }

    public int getData() {
        return j;
    }

    public class AddRunnable implements Runnable {

        ThreadShareDataTest shareDataTest;

        public AddRunnable(ThreadShareDataTest shareDataTest) {
            this.shareDataTest = shareDataTest;
        }

        @Override
        public void run() {
            shareDataTest.add();
        }
    }

    public class DecRunnable implements Runnable {

        ThreadShareDataTest shareDataTest;

        public DecRunnable(ThreadShareDataTest shareDataTest) {
            this.shareDataTest = shareDataTest;
        }

        @Override
        public void run() {
            shareDataTest.dec();
        }
    }

    /**
     * 场景1：将数据抽象成一个类，并将对这个数据的操作封装在类的方法中
     */
    @Test
    public void test_scene_v1() {
        ThreadShareDataTest shareData = new ThreadShareDataTest();
        Runnable add = new AddRunnable(shareData);
        Runnable dec = new DecRunnable(shareData);
        for (int i = 0; i < 2; i++) {
            new Thread(add).start();
            new Thread(dec).start();
        }

        /**
         * 运行结果：
         * 线程Thread-0j为：1
         * 线程Thread-1j为：0
         * 线程Thread-2j为：1
         * 线程Thread-3j为：0
         *
         * 结果分析：
         */
    }

    /**
     * 场景2：将Runnable对象作为一个类的内部类，将共享数据作为这个类的成员变量
     */
    @Test
    public void test_scene_v2() {
        final ThreadShareDataTest shareData = new ThreadShareDataTest();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    shareData.add();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    shareData.dec();
                }
            }).start();
        }

        /**
         * 输出结果：
         * 线程Thread-0j为：1
         * 线程Thread-1j为：0
         * 线程Thread-2j为：1
         * 线程Thread-3j为：0
         *
         * 结果分析：todo @Ms-01/28
         */
    }
}
