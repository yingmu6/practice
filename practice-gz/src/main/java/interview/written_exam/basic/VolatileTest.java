package interview.written_exam.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2023/8/9
 */
public class VolatileTest { //@MsY-Doing

    /**
     * 知识点：volatile_测试
     * 1）volatile能保证可见性、有序性，但不能保证原子性
     *
     * 问题点：
     * 1）volatile的底层原理是什么？
     */

    /**
     * 场景1：volatile不能保证原子性的场景
     */
    static volatile int intVal = 0;

    @Test
    public void test_volatile() {

        // 创建10个线程，执行简单的自加操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    intVal++;
                }
            }).start();
        }

        // 确保全部线程执行完毕
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(intVal);

        /**
         * 输出结果：（输出结果可能会变）
         * 10000
         *
         * 结果分析：
         */
    }
}
