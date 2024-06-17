package interview.written_exam.design_mode.singleton;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Singleton1 { //@MsY-Doing

    /**
     * 知识点：单例模式之懒汉式
     *
     * 知识点概括：
     * 1）实例在调用静态方法getInstance时创建
     * 2）将对象实例最为static成员变量，是使用到静态变量为所有对象所共享的特性
     */

    private static Singleton1 instance = null; //实例为静态成员变量

    private Singleton1() {} //构造方法时私有的

    public static Singleton1 getInstance() { //对外只提供static方法
        if (instance == null) {
            instance = new Singleton1();
        }

        return instance;
    }

    public static void main(String[] args) throws Exception {
       // basicUse();
        useMultiThread();
    }

    public static void basicUse() {
        Singleton1 o1 = Singleton1.getInstance();
        Singleton1 o2 = Singleton1.getInstance();
        System.out.println("o1 = " + o1);
        System.out.println("o2 = " + o2);
        System.out.println("(o1 == o2) is " + (o1 == o2));

        /**
         * 输出结果：
         * s1 = interview.written_exam.design_mode.singleton.Singleton1@244038d0
         * s2 = interview.written_exam.design_mode.singleton.Singleton1@244038d0
         * (s1 == s2) is true
         *
         * 结果分析：
         * 1）从输出结果来看，s1和s2是同一个引用，说明不管调用几次getInstance()，都是产生一个对象实例
         */
    }

    /**
     * 场景：使用多线程创建实例
     *
     * 备注：
     * 此处使用junit测试时，抛出java.lang.Exception: Test class should have exactly one public constructor
     * 也就是需要一个public的构造方法，但是加了public构造方法，就违反了单例模式中private构造方法的约定，所以用main方法测试
     */

//    @Test
    public static void useMultiThread() throws Exception {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        Future f1 = executors.submit(() -> {
            Singleton1 obj = Singleton1.getInstance();
            //TimeUnit.MILLISECONDS.
            return obj;
        });
        Future f2 = executors.submit(() -> {
            return Singleton1.getInstance();
        });
        Future f3 = executors.submit(() -> {
            return Singleton1.getInstance();
        });

        TimeUnit.SECONDS.sleep(2);
        System.out.println("s1 = " + f1.get());
        System.out.println("s2 = " + f2.get());
        System.out.println("s3 = " + f3.get());

        /**
         * 输出结果：
         * s1 = interview.written_exam.design_mode.singleton.Singleton1@3d075dc0
         * s2 = interview.written_exam.design_mode.singleton.Singleton1@3d075dc0
         * s3 = interview.written_exam.design_mode.singleton.Singleton1@3d075dc0
         *
         * 结果分析：
         * 1）用多线程模拟，不一定每次都会复现出线程不安全场景，存在不确定性
         */
    }
}
