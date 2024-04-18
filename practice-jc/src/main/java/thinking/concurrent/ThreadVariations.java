package thinking.concurrent;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class ThreadVariations {

    static class InnerThread1 {
        private int countDown = 5;
        private Inner inner;
        private class Inner extends Thread {
            Inner(String name) {
                super(name);
                start();
            }
            public void run() {
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
            inner = new Inner(name);
        }
    }

    static class InnerThread2 {
        private int countDown = 5;
        private Thread t;
        public InnerThread2(String name) {
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

    public static void main(String[] args) { //使用内部类将线程代码隐藏在类中将会很有用
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}
