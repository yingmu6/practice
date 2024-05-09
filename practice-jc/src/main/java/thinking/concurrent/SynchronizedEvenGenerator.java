package thinking.concurrent;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SynchronizedEvenGenerator extends IntGenerator { //@JaY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     * 1）
     */
    private int currentEventValue = 0;

    @Override
    public synchronized int next() {
        ++ currentEventValue;
        Thread.yield();
        ++ currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) { //同步控制EvenGenerator
        EvenChecker.test(new SynchronizedEvenGenerator());

        /**
         * 输出结果：（一直循环输出）
         * Press Control-C to exit
         * val = 938856
         * ....
         *
         * 结果分析：
         */
    }
}
