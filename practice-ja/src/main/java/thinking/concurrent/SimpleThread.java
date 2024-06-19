package thinking.concurrent;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SimpleThread extends Thread { //@TkY-Done

    /**
     * 知识点：编码的变体
     *
     * 知识点概括：
     * 1）在线程的构造方法中，设置线程名称、启动线程
     */
    private int countDown = 5;

    private static int threadCount = 0;

    public SimpleThread() {
        super(Integer.toString(++threadCount)); //设置线程名称
        start(); //启动线程
    }

    public String toString() {
        return "#" + getName() + "(" + countDown + ")，";
    }

    public void run() {
        while (true) {
            System.out.println(this); //打印 toString()方法中内容
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread(); //依次创建线程，线程的启动放在构造方法中
        }

        /**
         * 输出结果：（每次的输出结果，可能会变）
         * #1(5)，
         * #1(4)，
         * #5(5)，
         * #4(5)，
         * #4(4)，
         * #4(3)，
         * #3(5)，
         * #2(5)，
         * #3(4)，
         * #4(2)，
         * #5(4)，
         * #1(3)，
         * #1(2)，
         * #1(1)，
         * #5(3)，
         * #5(2)，
         * #5(1)，
         * #4(1)，
         * #3(3)，
         * #3(2)，
         * #3(1)，
         * #2(4)，
         * #2(3)，
         * #2(2)，
         * #2(1)，
         *
         * 结果分析：
         * 1）把线程的start()，放在构造方法中。并通过super(name)方式设置线程名称。
         */
    }
}
