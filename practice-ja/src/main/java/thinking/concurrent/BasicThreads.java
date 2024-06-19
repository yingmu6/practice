package thinking.concurrent;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class BasicThreads { //@TkY-Doing

    /**
     * 知识点：实现Runnable接口
     *
     * 知识点概括：
     *
     * 问题点答疑：
     * 1）LiftOff的run方法中Thread.yield();的用途是什么？没这语句，运行的结果也一样
     */

    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");

        /**
         * 输出结果：
         * Waiting for LiftOff
         * #0(9)，
         * #0(8)，
         * #0(7)，
         * #0(6)，
         * #0(5)，
         * #0(4)，
         * #0(3)，
         * #0(2)，
         * #0(1)，
         * #0(Liftoff!)，
         *
         * 结果分析：
         */
    }
}
