package thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class FixedThreadPool { //@TkY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     * 1）
     */

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();

        /**
         * 输出结果：（每次运行结果，可能不一样）
         * #1(9)，
         * #1(8)，
         * #1(7)，
         * #1(6)，
         * #1(5)，
         * #1(4)，
         * #1(3)，
         * #1(2)，
         * #2(9)，
         * #3(9)，
         * #3(8)，
         * #3(7)，
         * #3(6)，
         * #3(5)，
         * #3(4)，
         * #3(3)，
         * #0(9)，
         * #0(8)，
         * #4(9)，
         * #4(8)，
         * #4(7)，
         * #4(6)，
         * #4(5)，
         * #4(4)，
         * #4(3)，
         * #4(2)，
         * #4(1)，
         * #0(7)，
         * #3(2)，
         * #3(1)，
         * #2(8)，
         * #1(1)，
         * #2(7)，
         * #1(Liftoff!)，
         * #3(Liftoff!)，
         * #0(6)，
         * #0(5)，
         * #0(4)，
         * #0(3)，
         * #4(Liftoff!)，
         * #0(2)，
         * #2(6)，
         * #2(5)，
         * #2(4)，
         * #2(3)，
         * #2(2)，
         * #2(1)，
         * #2(Liftoff!)，
         * #0(1)，
         * #0(Liftoff!)，
         *
         * 结果分析：
         * 1）
         */
    }
}
