package relative.basic.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类使用
 * 线程安全: 当多个线程访问某个类时, 这个类始终都能表现出正确的行为, 那么就称这个类是线程安全的。
 * @author : chensy
 * Date : 2020-02-16 21:14
 */
public class AtomicUse {
    private static int num = 0;
    private static int sum = 0;
    private static AtomicInteger aNum = new AtomicInteger();

    public static void main(String[] args) {
        //basicUse();
        unsafeUse1();
        unsafeUse2();
        unsafeUse3();
        // todo 此处为啥输出的值每次都不一样？
    }

    // 基本使用
    public static void basicUse() {
        AtomicInteger ai = new AtomicInteger(0);
        int a = ai.getAndIncrement(); // 获取当前的值，并自增
        System.out.println(ai.get()); // 返回1
        int b = ai.getAndIncrement();
        System.out.println(ai.get());
        System.out.println("a=" + a + ",b=" + b); //输出：a=0,b=1
    }

    // 模拟 ++i以及i++ 线程不安全
    public static void unsafeUse1() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 1; i <= 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    num++;
                    //System.out.println("自增值test1：num=" + (num++)); // 有概率出现顺序是乱序的
                }
            });
        }
        System.out.println("最终值test1：num=" + num);

    }

    public static synchronized void unsafeUse2() { // 加上synchronized 不会乱序，是顺序的
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 1; i <= 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    sum++;
                    //System.out.println("自增值test2：num=" + (num++));
                }
            });
        }
        System.out.println("最终值test2：sum=" + sum);

    }

    public static void unsafeUse3() { // 使用原子
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 1; i <= 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    aNum.getAndIncrement();
                }
            });
        }
        System.out.println("最终值test3：aNum=" + aNum.intValue());
    }

}

/**
 * 在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，
 * 不可避免的会用到synchronized关键字。而AtomicInteger则通过一种线程安全的加减操作接口。
 *
 *     //获取当前的值
 *     public final int get()
 *
 *     //取当前的值，并设置新的值
 *     public final int getAndSet(int newValue)
 *
 *     //获取当前的值，并自增
 *     public final int getAndIncrement()
 *
 *     //获取当前的值，并自减
 *     public final int getAndDecrement()
 *
 *     //获取当前的值，并加上预期的值
 *     public final int getAndAdd(int delta)
 */


