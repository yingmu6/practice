package thinking.concurrent;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Joining { //@TkY-Doing

    /**
     * 知识点：加入一个线程
     *
     * 知识点概括：
     * 1）加入一个线程，等待加入的线程执行完成后，当前线程才能执行
     * 2）join()方法，底层会判断加入的线程是否存活的isAlive()，若存活则使用wait()方法进行等待。
     */
    static class Sleeper extends Thread {
        private int duration;
        public Sleeper(String name, int sleepTime) {
            super(name);
            duration = sleepTime;
            start(); //启动线程
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                sleep(duration);
            } catch (InterruptedException e) { //线程被中断
                print(getName() + " was interrupted. " + "isInterrupted()：" + isInterrupted());
                return;
            }
            print(getName() + " has awakened，耗时：" + (System.currentTimeMillis() - startTime) + " 毫秒"); //睡眠指定duration后恢复，即被唤醒
        }
    }

    static class Joiner extends Thread {
        private Sleeper sleeper;
        public Joiner(String name, Sleeper sleeper) {
            super(name);
            this.sleeper = sleeper;
            start();
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                sleeper.join(); //加入线程（等待当前加入的线程运行，直到结束）
            } catch (InterruptedException e) {
                print("Interrupted");
            }

            try {
                sleep(1000); //执行Joiner线程的逻辑
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            print(getName() + " join completed，耗时：" + (System.currentTimeMillis() - startTime) + " 毫秒");
        }
    }


    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper("Sleepy", 5000),
                grumpy = new Sleeper("Grumpy", 10000);

        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();

        /**
         * 输出结果：
         * Grumpy was interrupted. isInterrupted()：false
         * Doc join completed，耗时：1020 毫秒
         * Sleepy has awakened，耗时：5005 毫秒
         * Dopey join completed，耗时：6005 毫秒
         *
         * 结果分析：
         * 1）创建Sleeper类型的两个线程sleepy、grumpy，线程的执行体run()中会通过sleep睡眠指定的时间
         * 2）创建Joiner类型的两个线程dopey、doc，线程的执行体run()中会将传入的Sleeper类型的线程调用join()
         *    也就是说Joiner线程会等Sleeper线程执行完成才执行
         * 3）如果线程grumpy被中断了，也就是不是存活的线程，线程加入时，就不需要等待，直接执行Joiner线程
         *    3.1）所以直接执行名为"Doc"的Joiner类型的线程，因为它run()方法里面sleep(1000)，所以输出"...耗时：1020 毫秒"
         *    3.2）名为"dopey"的Joiner类型的线程，run()当中是对名为"Sleepy"的Sleeper线程进行join，因为该线程是正常运行的，
         *         所以需要等待该线程正常运行结束，所以最终输出"Sleepy has awakened，耗时：5005 毫秒"
         *    3.3）当"Sleepy"线程执行完成后，才继续执行"Dopey"线程
         */
    }
}
