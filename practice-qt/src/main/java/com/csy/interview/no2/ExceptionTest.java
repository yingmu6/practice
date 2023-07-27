package com.csy.interview.no2;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/7/27
 */
public class ExceptionTest {

    /**
     * 异常类型_测试
     * 1）异常的分类：
     *    a）检查性异常：最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。这些异常在编译时不能被简单地忽略。（编译期间发现的异常）
     *    b）运行时异常： 运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以在编译时被忽略。（运行期间发现的异常）
     *    c）错误： 错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-exceptions（其用例可参考）
     */

    /**
     * 场景1：运行时异常，可以不用try/catch或方法上声明异常
     */
    @Test
    public void test_checked_exception() {
        doSomething();
        // doSomething2(); 此处需要捕获异常，因为IOException属于检查时异常
    }

    private void doSomething() throws ArithmeticException{ //ArithmeticException是运行时异常，继承了RuntimeException（可以在编译器被忽略）
        System.out.println("test");
    }

    private void doSomething2() throws IOException {
        System.out.println("test2");
    }
}
