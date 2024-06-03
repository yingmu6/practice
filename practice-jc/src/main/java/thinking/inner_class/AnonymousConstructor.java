package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class AnonymousConstructor { //@TkY-Done

    /**
     * 知识点：匿名内部类
     */

    static abstract class Base {
        public Base(int i) { //从debug观察进入当前构造方法的流程：<init>：AnonymousConstructor$1 =》<init>: AnonymousConstructor$Base
            System.out.println("Base constructor，i =" + i);
        }

        public abstract void fn();
    }

    public static Base getBase(int i) {
        return new Base(i) { //创建匿名内部类

            { //代码块（在构造方法前执行）
                System.out.println("Inside instance initializer");
            }

            @Override
            public void fn() {
                System.out.println("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
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
         */
    }
}
