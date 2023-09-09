package com.csy.interview.no2;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/9/9
 */
public class SynchronizedTest {

    /**
     * synchronized_测试
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-synchronized
     */

    /**
     * 场景1：访问非synchronized修饰的方法
     */
    @Test
    public void test_access_synchronized_method() throws IOException {
        SynchronizedTest t = new SynchronizedTest();
        Thread t1 = new Thread() {
            public void run() {
                t.synchronizedMethod(); // 1）
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                t.generalMethodV1(); // 2）
                t.generalMethodV2(); // 3)
                t.generalMethodV3(); // 4)
            }
        };

        t1.start();
        t2.start();

        System.in.read();

        /**
         * 输出结果：
         * begin calling
         * call generalMethodV1
         * call generalMethodV2
         * finish calling synchronizedMethod
         * call generalMethodV3
         *
         * 结果分析：
         * 1）synchronizedMethod方法中synchronized是作用在对象的普通方法上，所以用的是对象锁
         * 2）generalMethodV1是普通方法，没有加上synchronized修饰，也就是没加锁，在线程占用synchronizedMethod方法期间，其它线程可以访问该方法
         * 3）generalMethodV2是静态方法，加上了synchronized修饰，使用的是类所，与synchronizedMethod使用的对象锁不一样，所以其它线程可访问，不受限制
         * 4）generalMethodV3是普通方法，加上了synchronized修饰，使用的是对象锁，所以synchronizedMethod在未释放锁的时候，generalMethodV3是不可以访问的
         *
         * 结果总结：
         * 1）需要判断synchronized作用在对象上还是类上，判断是对象锁还是类所，从而判断使用的是否是同一把锁。若是同一把锁，就会独占，只有前一个线程释放了，后面的线程才能使用
         */
    }

    public synchronized void synchronizedMethod() {
        System.out.println("begin calling");
        try {
            Thread.sleep(10000); //线程阻塞10秒
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("finish calling synchronizedMethod");
    }

    public void generalMethodV1() {
        System.out.println("call generalMethodV1");
    }

    public static void generalMethodV2() { //使用的是类所
        System.out.println("call generalMethodV2");
    }

    public synchronized void generalMethodV3() { //使用的是对象锁
        System.out.println("call generalMethodV3");
    }
}
