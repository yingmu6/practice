package com.csy.interview.offer_come.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class SemaphoreUseTest { //@MsY-Doing

    /**
     * 知识点：Semaphore
     *
     * 知识点概括：
     *
     */

    public static void main(String[] args) {
        // basicUse();
        /**
         * 输出结果：
         *
         * 结果分析：
         */

        basicUse2();
        /**
         * 输出结果：
         * 员工5占用一个打印机...
         * 员工5占用一个打印机...
         * 员工5打印完成，释放出打印机
         * 员工5打印完成，释放出打印机
         * 员工5占用一个打印机...
         * 员工5占用一个打印机...
         * 员工5打印完成，释放出打印机
         * 员工5打印完成，释放出打印机
         * 员工5占用一个打印机...
         * 员工5打印完成，释放出打印机
         *
         * 结果分析：
         */
    }

    public static void basicUse() {
        Semaphore semaphore = new Semaphore(5);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        } finally {
            semaphore.release();
        }
    }

    public static void basicUse2() {
        int printNumber = 5;
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < printNumber; i++) {
            new Worker(printNumber, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;
        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("员工" + this.num + "占用一个打印机...");
                Thread.sleep(2000);
                System.out.println("员工" + this.num + "打印完成，释放出打印机");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
