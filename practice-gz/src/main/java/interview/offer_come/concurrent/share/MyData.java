package interview.offer_come.concurrent.share;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class MyData { //@MsY-Doing

    /**
     * 场景：多线程共享数据（将数据抽象成一个类，并将对这个数据的操作封装在类的方法中）
     *
     * 问题点：
     * 1）用例本意是想表达什么？
     */
    public static void main(String[] args) {
       MyData data = new MyData();
       Runnable add = new AddRunnable(data);
       Runnable dec = new DecRunnable(data);
        for (int i = 0; i < 2; i++) {
            new Thread(add).start();
            new Thread(dec).start();
        }

        /**
         * 结果输出：（每次结果会有变动）
         * 线程Thread-0，j为：1
         * 线程Thread-2，j为：2
         * 线程Thread-1，j为：1
         * 线程Thread-3，j为：0
         *
         * 结果分析：
         */
    }

    private int j = 0;
    public synchronized void add() {
        j++;
        System.out.println("线程" + Thread.currentThread().getName() + "，j为：" + j);
    }

    public synchronized void des() {
        j--;
        System.out.println("线程" + Thread.currentThread().getName() + "，j为：" + j);
    }

    static public class AddRunnable implements Runnable {

        MyData data;

        public AddRunnable(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.add();
        }
    }

    static public class DecRunnable implements Runnable {

        MyData data;

        public DecRunnable(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.des();
        }
    }

}
