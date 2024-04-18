package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MultiInterfaces {

    interface A {}

    interface B {}

    static class X implements A, B {
    }

    static class Y implements A {
        B makeB() {
            return new B(){};
        }
    }

    static void tasksA(A a) {}

    static void tasksB(B b) {}

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();
        MultiInterfaces.tasksA(x);
        MultiInterfaces.tasksA(y);
        MultiInterfaces.tasksB(x);
        MultiInterfaces.tasksB(y.makeB());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }
}
