package thinking.inner_class.case5;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Callbacks {
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
         * （场景：闭包与回调）
         *
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
         *
         * 总结概括：
         */
    }
}
