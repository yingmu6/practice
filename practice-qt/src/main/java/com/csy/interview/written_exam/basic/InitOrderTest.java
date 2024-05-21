package com.csy.interview.written_exam.basic;

import com.csy.interview.written_exam.basic.init_ext.Child;
import com.csy.interview.written_exam.basic.init_ext.InitB;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class InitOrderTest { //@MsY-Done

    /**
     * 知识点：程序初始化顺序
     *
     * 知识点概括：
     * 1）先初始化静态块：父类的静态块 -> 子类的静态块
     * 2）再初始化非静态块：
     *    a）父类的非静态块 -> 父类的构造方法
     *    b）子类的非静态块 -> 子类的构造方法
     */

    /**
     * 场景1：父类、子类的初始化顺序
     */
    @Test
    public void test_init_order() { //@MsY-Done
        new Child();

        /**
         * 输出结果：
         *
         * 父类静态块
         * 子类静态块
         * 父类非静态块
         * 父类构造方法
         * 子类非静态块
         * 子类构造方法
         *
         */
    }

    static {a = 2;} //静态块中初始化
    static int a = 1; //成员域中初始化（从上到下加载，以最后一次初始化为最终的）
    static int b = 3;

    static {b = 4;}

    {c = 5;} //可以放在声明的上面
    int c = 3;

    /**
     * 场景2：块、成员域中初始化顺序
     */
    @Test
    public void test_order() { //Done
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        /**
         * 输出结果：
         * 1
         * 4
         * 3
         *
         * 结果分析：
         * 块中的初始化与成员域中的初始化是平级的，
         * 所以会按照从上到下初始化，最后一次初始化为最终的值
         */
    }

    /**
     * 场景3：静态变量的调用
     */
    @Test
    public void test_static_var() { //Done
        System.out.println(InitB.c);

        /**
         * 输出结果：
         * A
         * C
         *
         * 结果分析：
         * 因为调用InitB.c，static变量c在A类中，所以会加载父类A，所以输出了静态块中的"A"
         * 因为InitB.c直接访问InitA的变量，不需要访问InitB中的其它内容，所以就不要加载InitB了
         */
    }

    /**
     * 场景4：使用ClassLoader、以及class#forName加载类时，静态块的执行
     */
    @Test
    public void test_static_block() throws Exception { //Done
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class clazz = classLoader.loadClass("com.csy.interview.written_exam.basic.init_ext.InitA");
        clazz.forName("com.csy.interview.written_exam.basic.init_ext.InitA");

        String className = this.getClass().getPackage() + ".InitA";
        System.out.println(className);

        /**
         * 输出结果：
         * A
         * package com.csy.interview.written_exam.basic.InitA
         *
         * 结果分析：
         * ClassLoader加载类，不会导致类的初始化。
         * 而Class.forName()方法不仅会加载类，而且还会执行类的初始化方法
         */
    }
}
