package com.csy.interview.offer_come.concurrent.lock;

import org.junit.Test;

import java.io.IOException;

/**
 * @author chensy
 * @date 2023/9/9
 */
public class SynchronizedTest { //@MsY-done

    /**
     * 知识点：synchronized
     *
     * 知识点概括：
     * 1）需要判断synchronized作用在对象上还是类上，判断是对象锁还是类所，从而判断使用的是否是同一把锁。若是同一把锁，就会独占，只有前一个线程释放了，后面的线程才能使用
     * 2）synchronized作用在普通方法上，即为对象锁。作用在static方法上，即为类锁。若没有加上synchronized，则是没有加锁。
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
        Thread t1 = new Thread() { //匿名类
            public void run() {
                t.synchronizedMethod(); // 1）synchronized作用在普通方法上，属于对象锁
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                t.generalMethodV1(); // 2）没有加synchronized修饰，未使用锁
                t.generalMethodV2(); // 3) synchronized作用在static方法上，属于类锁
                t.generalMethodV3(); // 4) synchronized作用在普通方法上，属于对象锁
            }
        };

        t1.start();
        t2.start();

        System.in.read();

        /**
         * 输出结果：
         * begin calling synchronizedMethod
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
         */
    }

    public synchronized void synchronizedMethod() {
        System.out.println("begin calling synchronizedMethod");
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

    public synchronized static void generalMethodV2() { //使用的是类锁
        System.out.println("call generalMethodV2");
    }

    public synchronized void generalMethodV3() { //使用的是对象锁
        System.out.println("call generalMethodV3");
    }
}
