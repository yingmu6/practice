package interview.written_exam.basic;

/**
 * @author chensy
 * @date 2024/5/20
 */
public class InterfaceTest { //@MsY-Done

    /**
     * 知识点：接口访问
     */

    public static void main(String[] args) {
        /**
         * 接口中的变量访问
         */
        System.out.println(InterfaceDefine.var1);
        System.out.println(InterfaceDefine.var2);
        System.out.println(InterfaceDefine.var3);

        A a = new A();
        a.f1();

        /**
         * 结果分析：
         * 1）接口中的变量都是public static final修饰的，即为静态常量。
         *    可以声明出来，也可不声明，且可部分声明
         *
         * 2）接口中的方法都是public abstract修饰的，即为抽象方法
         *    可以声明出来，也可不声明，且可部分声明
         *
         * 3）接口中，可以声明默认方法default、静态方法static，实现类中可以选择实现
         */
    }

    static class A implements InterfaceDefine { //实现接口，就要实现接口中所有普通方法

        @Override
        public void f1() { //重写的方法，权限不能降低，此处若写protected、private就会编译错误
            System.out.println("f1");
        }

        @Override
        public void f2() {
            System.out.println("f2");
        }

    }

    abstract class B implements InterfaceDefine { //抽象类，可以不用重新接口中的普通方法

    }

}
