package thinking.inner_class.case3;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MultiNestingAccess {
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
