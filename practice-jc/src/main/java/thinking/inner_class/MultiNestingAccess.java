package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class MultiNestingAccess {

    static class MNA {
        private void f() {
            System.out.println("MNA f()");
        }

        class A {
            private void g() {
                System.out.println("A g()");
            }
            public class B {
                void h() {
                    System.out.println("B h()");
                    g();
                    f();
                }
            }
        }
    }

    /**
     * 场景1：多个嵌套的内部类
     */
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();

        /**
         * 输出结果：
         * B h()
         * A g()
         * MNA f()
         *
         * 结果分析：
         * 1）MNA中嵌套了A，A中嵌套了B。
         * 2）先构造外部类的实例mnaa、mnaab，最后再创建内部类
         *
         * 总结概括：
         * 1）内部类可以实现多层级的嵌套
         *
         */
    }

}
