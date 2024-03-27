package thinking.inner_class.case4;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MultiImplementation {

    static void takesD(D d) {}

    static void takesE(E e) {}

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
