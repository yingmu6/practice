package com.csy.interview.written_exam.thread;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author chensy
 * @date 2023/9/18
 */
public class RecursiveTaskTest { //@MsY-Doing

    /**
     * 知识点：RecursiveTask
     *
     * 知识点概要：
     * 1）Fork/Join是JDK7中出现的高效工具，可以将大任务拆分为小任务后并行运行，然后将小任务的最终结果合并成大任务的结果。
     *
     * 2）使用Fork/Join的一个前提条件是：任务的分割必须保证子任务独立，也就是子任务之间没有相互依赖关系。
     *
     * 3）工作原理：线程池中的每个线程都有自己的工作队列，当自己队列中的任务都完成了，会从其他线程中的工作队列中"偷"一个任务执行
     *    这样可以充分利用资源，提高任务的执行效率，这种思想被称之为"工作窃取算法"。
     *
     * 4）为了减少窃取任务线程和被窃取任务线程之间的竞争，使用了双端队列，被窃取任务线程只能从队列头部获取任务执行，窃取任务线程只能从队列尾部获取任务执行
     *   （但是在队列中只有一个任务时，还是会产生竞争）
     *
     * 5）相关的类和接口：
     *    a）ForkJoinPool类：执行任务分割的线程池，实现ExecutorService接口，即有三种执行任务方式，execute、submit、invoke和invokeAll
     *    b）ForkJoinTask抽象类：实现类Future接口，是一个异步任务。包含fork、join、invoke等方法，fork：创建子任务，join：等待任务完成合并计算结果
     *    c）RecursiveTask：异步任务，有返回结果；RecursiveAction：异步任务，无返回结果
     *
     * 问题点答疑：
     * 1）RecursiveTask内部逻辑是怎么实现分拆、合并的？
     *
     * 参考链接：
     * a）https://juejin.cn/post/7119723548729573413 Fork/Join源码分析（分析的比较全面）
     * b）https://www.jianshu.com/p/6a14d0b54b8d ForkJoinPool源码分析（由流程图，比较形象）
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_basic() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        SumTask task = new SumTask(1, 10);
        Future<Integer> result = forkJoinPool.submit(task); //提交任务到线程池中

        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 输出结果：
         *
         * 计算加法区间为：1~3
         * 计算加法区间为：4~5
         * 计算加法区间为：9~10
         * 计算加法区间为：6~8
         * 55
         *
         * 结果分析：
         * 1）计算区间的前后位置，可能在每次运行会变，但最终计算的结果相同的
         *
         */
    }

    class SumTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 3;

        private int start;

        private int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            boolean smallEnough = (end - start) <= THRESHOLD;
            if (smallEnough) { //满足计算条件，则做对应计算
                System.out.println("计算加法区间为：" + start + "~" + end);
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                int mid = (start + end) / 2; //二分法（依次取中点值，分为左半部分、右半部分进行计算）
                SumTask task1 = new SumTask(start, mid);
                SumTask task2 = new SumTask(mid + 1, end);

                task1.fork(); //创建子任务（最终会创建ForkJoinPool#createWorker工作线程，并启动线程）
                task2.fork();

                if (task1.isCompletedAbnormally()) {
                    System.out.println(task1.getException());
                }

                if (task2.isCompletedAbnormally()) {
                    System.out.println(task2.getException());
                }

                int task1Result = task1.join(); //阻塞等待任务完成，并返回计算的结果
                int task2Result = task2.join();

                sum = task1Result + task2Result;
            }
            return sum;
        }
    }
}
