package com.csy.interview.written_exam.basic;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/7/25
 */
public class FinallyTest {

    /**
     * finally_测试
     * 1）在try关键字最后可以定义finally代码块。
     * 2）finally块中定义的代码，总是在try和任何catch 块之后、方法完成之前运行。
     * 3）正常情况下，不管是否抛出或捕获异常 finally 块都会执行。
     *
     * 参考链接：https://juejin.cn/post/6844904039402962958 java finally用法（讲的比较详细）
     */

    /**
     * 场景1：测试执行顺序
     */
    @Test
    public void test_finally_order_v1() {
        try {
            System.out.println("before statement");
            throw new NullPointerException();
        } catch (Exception e) {
            System.out.println("happen error");
        } finally {
            System.out.println("finally statement");
        }
        System.out.println("after statement");

        /**
         * 输出结果：
         * before statement
         * finally statement
         * after statement
         *
         * 结果分析：
         * finally在try语句块后，方法完成前之前
         */
    }

    @Test
    public void test_finally_order_v2() {
        System.out.println(sayHello());

        /**
         * 输出结果：
         * finally statement
         * hello
         *
         * 结果分析：
         * 因为finally在try语句块后、方法完成前执行。因为遇到return，方法会结束。所以在return前，就会执行finally
         */
    }

    private String sayHello() {
        try {
            return "hello"; //遇到return，方法执行完成
        } finally {
            System.out.println("finally statement");
        }
    }

    /**
     * 场景2：finally不会执行
     * 1）调用System#exit
     * 2）调用Runtime#halt函数
     * 3）守护线程
     * 4）try代码块中无限循环
     */
    @Test
    public void test_finally_no_execution_v1() { //调用System.exit
        try {
            System.out.println("try statement");
            System.exit(1);
        } finally {
            System.out.println("finally statement");
        }

        /**
         * 输出结果：
         * try statement
         *
         * 结果分析：
         * 调用System.exit后，就会中断了程序，所以就不会执行后面的finally内容了
         */
    }

    @Test
    public void test_finally_no_execution_v2() {
        try {
            System.out.println("try statement");
            Runtime.getRuntime().halt(1);
        } finally {
            System.out.println("finally statement");
        }

        /**
         * 输出结果：
         * try statement
         *
         * 结果分析：
         * Runtime#halt会强制中断JVM，方法不会正常返回（halt /hɔːlt/：中断、阻止）
         */
    }

    @Test
    public void test_finally_no_execution_v3() throws InterruptedException, IOException {
        Runnable runnable = () -> {
            try {
                System.out.println("try statement");
            } finally {
                try {
                    Thread.sleep(1000);
                    System.out.println("finally statement");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread regular = new Thread(runnable);
        Thread daemon = new Thread(runnable);
        daemon.setDaemon(true);
        daemon.start();
        Thread.sleep(300);
        regular.start();

        System.in.read();

        /**
         * 输出结果：
         * try statement
         * try statement
         * finally statement
         * finally statement
         *
         * 结果分析：(输出的结果内容有些歧义，待分析确认)
         * 如果守护线程刚开始执行到finally代码块，此时没有任何其他非守护线程，那么虚拟机将退出，
         * 此时JVM不会等待守护线程的finally 代码块执行完成。
         */
    }

    @Test
    public void test_finally_no_execution_v4() {
        try {
            System.out.println("try statement");
            while (true) {
            }
        } finally {
            System.out.println("finally statement");
        }

        /**
         * 输出结果：
         * try statement（一直循环中）
         *
         * 结果分析
         * 因为while无限循环，一直执行不到finally
         */
    }

    /**
     * 场景3：finally的陷阱
     * 1）忽视异常
     * 2）覆盖其他的返回语句
     * 3）改变throw或return行为
     */
    @Test
    public void test_finally_trap_v1() {
        System.out.println(sayHello2());

        /**
         * 输出结果：
         * try statement
         * finally statement
         *
         * 结果分析：
         * finally中含有return语句，也没有通过catch语句捕获异常，所以异常被忽视了
         */
    }

    private String sayHello2() {
        try {
            System.out.println("try statement");
            throw new RuntimeException();
        } finally {
            System.out.println("finally statement");
            return "from finally";
        }
    }

    @Test
    public void test_finally_trap_v2() {
        System.out.println(sayHello3());

        /**
         * 输出结果：
         * try statement
         * finally statement
         * from finally
         *
         * 结果分析：
         * 如果finally中存在return语句，则try和catch代码块中的返回语句就会被忽略
         */
    }

    private String sayHello3() {
        try {
            System.out.println("try statement");
            return "from try";
        } finally {
            System.out.println("finally statement");
            return "from finally";
        }
    }

    @Test
    public void test_finally_trap_v3() {
        try {
            System.out.println(sayHello4());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /**
         * 输出结果：
         * try statement
         * null
         *
         * 结果分析：
         * 因为finally代码块扔出异常，则 try 和 catch 中的异常扔出或者返回语句都将被忽略。
         */
    }

    private String sayHello4() {
        try {
            System.out.println("try statement");
            return "from try";
        } finally {
            throw new RuntimeException();
        }
    }
}

