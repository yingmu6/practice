package thinking.inner_class.basic;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ZdTest {

    /**
     * 场景1：匿名内部类使用
     */
    @Test
    public void basicUse() {
        Base base = AnonymousConstructor.getBase(47);
        base.fn();

        /**
         * 输出结果：
         * Base constructor，i =47
         * Inside instance initializer
         * In anonymous f()
         *
         * 结果分析：
         * 1）getBase()方法中返回匿名内部类的实例，而创建实例时，会先调用父类的构造方法，即Base()
         * 2）父类构造完成，就会进行子类的初始化，先处理代码块，再执行构造方法，最后再执行调用的方法fn()
         *
         * 总结概括：
         * 1）匿名内部类的实例创建，也会遵循普通类继承的执行顺序
         */
    }
}
