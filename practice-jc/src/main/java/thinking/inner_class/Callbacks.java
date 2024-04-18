package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Callbacks {

    /**
     * 知识点：闭包与回调
     *
     * 知识点概括：
     *
     * 问题点答疑：
     * 1）什么是闭包？
     *
     */

    interface Incrementable {
        void increment();
    }

    static class MyIncrement {

        public void increment() {
            System.out.println("Other operation");
        }

        static void f(MyIncrement mi) {
            mi.increment(); //可访问创建对象的方法（说明传入的实例，在当前类初始化前就创建好的）
            // increment(); //此处会编译错误，静态方法不能调用非静态方法
        }
    }

    static class Callee1 implements Incrementable {

        private int i = 0;

        @Override
        public void increment() {
            i++;
            System.out.println(i);
        }
    }

    static class Callee2 extends MyIncrement {

        private int i = 0;

        public void increment() {
            super.increment();
            i++;
            System.out.println(i);
        }

        private class Closure implements Incrementable { //closure：闭包

            private int i = 0;

            @Override
            public void increment() {
                Callee2.this.increment();
            }
        }

        Incrementable getCallbackReference() {
            return new Callee2.Closure();
        }
    }

    static class Caller {

        private Incrementable callbackReference;

        Caller(Incrementable cbh) {
            callbackReference = cbh;
        }

        void go() {
            callbackReference.increment();
        }
    }

    public static void main(String[] args) {
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
