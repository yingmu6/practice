package thinking.inner_class.case4;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MultiInterfaces {

    static void tasksA(A a) {}

    static void tasksB(B b) {}

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();
        tasksA(x);
        tasksA(y);
        tasksB(x);
        tasksB(y.makeB());

        /**
         * （场景：解决多重继承）
         *
         * 输出结果：
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
