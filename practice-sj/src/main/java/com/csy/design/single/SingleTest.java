package com.csy.design.single;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class SingleTest {

    /**
     * 单例_测试
     * 1）单例模式：一个类只有一个实例，并提供一个全局访问点
     *
     * 2）应用场景：线程池、缓存、注册表等
     *
     * 3）各种写法：饿汉模式、懒汉模式、双重检查锁模式
     *
     * 4）单例的特点：
     *    a）构造方法时私有的
     *    b）提供全局访问点
     *
     * 参考链接：
     * a）https://juejin.cn/post/6844904105891250189 单例模式的分类
     */

    /**
     * 场景1：饿汉式模式
     */
    @Test
    public void test_eager_singleton() {
        EaGerStyle eaGerStyle = EaGerStyle.getInstance();
        EaGerStyle eaGerStyleV2 = EaGerStyle.getInstance();

        System.out.println(eaGerStyle == eaGerStyleV2); //输出true
    }

    /**
     * 场景2：懒汉式模式（线程安全）
     */
    @Test
    public void test_idler_safe() throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Callable<IdlerStyle> callable = new Callable<IdlerStyle>() {
                @Override
                public IdlerStyle call() throws Exception {
                    Thread.sleep(100);
                    IdlerStyle idlerStyle = IdlerStyle.getInstance();
                    System.out.println("实例值=" + idlerStyle); //该种方式，输出的对象引用是一样的
                    return idlerStyle;
                }
            };

            executor.submit(callable);
        }
        System.in.read(); //需要加上这句话，不然方法结束了，线程池还没来得及执行，就没有输出了
    }

    @Test
    public void test_idler_safe_V2() throws IOException {
        for (int i = 0; i < 10; i++) {
            new Thread() {

            }.start();
        }
        System.in.read();
    }

}
