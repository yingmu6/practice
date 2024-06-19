package thinking.concurrent;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class ThreadVariations { //@JaY-Doing

    /**
     * 知识点：使用内部类将线程代码隐藏在类中将会很有用
     *
     * 知识点概括：
     * 1）
     *
     * 问题点答疑：
     * 1）内部类隐藏线程代码，有用是体现在哪里？
     */

    static class InnerThread1 {
        private int countDown = 5;
        private Inner inner;
        private class Inner extends Thread { //内部类
            Inner(String name) {
                super(name);
                start(); //启动线程
            }
            public void run() { //使用内部类把线程代码隐藏
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0) return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("interrupted");
                }
            }
            public String toString() {
                return getName() + "：" + countDown;
            }
        }
        public InnerThread1(String name) {
            inner = new Inner(name); //构建内部类实例
        }
    }

    static class InnerThread2 {
        private int countDown = 5;
        private Thread t;
        public InnerThread2(String name) {
            t = new Thread(name) { //匿名内部类
                public void run() {
                    try {
                        while (true) {
                            print(this);
                            if (--countDown == 0) return;
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        print("sleep() interrupted");
                    }
                }
                public String toString() {
                    return getName() + "：" + countDown;
                }
            };
            t.start();
        }
    }

    static class InnerRunnable1 {
        private int countDown = 5;
        private Inner inner;
        private class Inner implements Runnable {
            Thread t;
            Inner(String name) {
                t = new Thread(this, name);
                t.start();
            }
            @Override
            public void run() {
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0) return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("sleep() interrupted");
                }
            }

            public String toString() {
                return t.getName() + "：" + countDown;
            }
        }

        public InnerRunnable1(String name) {
            inner = new Inner(name);
        }
    }

    static class InnerRunnable2 {
        private int countDown = 5;
        private Thread t;
        public InnerRunnable2(String name) {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            print(this);
                            if (--countDown == 0) return;
                            TimeUnit.MILLISECONDS.sleep(10);
                        }
                    } catch (InterruptedException e) {
                        print("sleep() interrupted");
                    }
                }

                public String toString() {
                    return Thread.currentThread().getName() +
                            "：" + countDown;
                }
            }, name);
            t.start();
        }
    }

    static class ThreadMethod {
        private int countDown = 5;
        private Thread t;
        private String name;
        public ThreadMethod(String name) {
            this.name = name;
        }

        public void runTask() {
            if (t == null) {
                t = new Thread(name) {
                    public void run() {
                        try {
                            while (true) {
                                print(this);
                                if (--countDown == 0) return;
                                sleep(10);
                            }
                        } catch (InterruptedException e) {
                            print("sleep() interrupted");
                        }
                    }

                    public String toString() {
                        return getName() + "：" + countDown;
                    }
                };
                t.start();
            }
        }
    }

    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();

        /**
         * 输出结果：（输出顺序可能会变）
         * InnerThread2：5
         * InnerRunnable1：5
         * InnerThread1：5
         * InnerRunnable2：5
         * ThreadMethod：5
         * InnerRunnable1：4
         * ThreadMethod：4
         * InnerRunnable2：4
         * InnerThread2：4
         * InnerThread1：4
         * InnerRunnable1：3
         * InnerThread2：3
         * InnerRunnable2：3
         * ThreadMethod：3
         * InnerThread1：3
         * InnerRunnable1：2
         * InnerThread2：2
         * ThreadMethod：2
         * InnerRunnable2：2
         * InnerThread1：2
         * InnerRunnable1：1
         * InnerThread2：1
         * ThreadMethod：1
         * InnerRunnable2：1
         * InnerThread1：1
         *
         * 结果分析：
         * 1）把线程的创建、启动start()都放在内部类中
         */
    }
}
