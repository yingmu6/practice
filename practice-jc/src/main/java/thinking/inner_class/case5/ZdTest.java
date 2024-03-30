package thinking.inner_class.case5;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class ZdTest {

    /**
     * 知识点：闭包与回调
     *
     * 知识点概括：
     *
     * 问题点答疑：
     * 1）什么是闭包？
     *
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void basicUse() {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();

        /**
         * 输出结果：
         * Other operation
         * 1
         * 1
         * 2
         * Other operation
         * 2
         * Other operation
         * 3
         *
         * 结果分析：
         * 1）Callee1实现了Incrementable接口，Callee2实现了MyIncrement接口，虽然没有实现Incrementable接口，但通过成员内部类Closure实现了，
         *    在Callee2中就可以对Incrementable接口的组合，到达多重继承的效果。
         * 2）MyIncrement.f(c2) 调用时，会执行Callee2中的increment()方法，刚方法中又执行了super.increment();所以最终会先执行MyIncrement，再执行Callee2中方法
         * 3）通过c2.getCallbackReference()获取到内部类Closure的实例，最终实现Increment接口的实例方法调用
         *
         * 总结概括：
         *
         */
    }
}
