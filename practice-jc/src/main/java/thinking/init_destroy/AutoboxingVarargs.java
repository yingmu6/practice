package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class AutoboxingVarargs {

    /**
     * 知识点（5.8.1）：
     *
     * 问题点答疑：
     * 1）可变参数的语法形式有哪些？
     */

    public static void f(Integer... args) {
        for (Integer i : args) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        f(new Integer(1), new Integer(2));
        f(4, 5, 7, 8, 9);
        f(10, new Integer(11), 12);

        /**
         * 输出结果：
         * 1 2
         * 4 5 7 8 9
         * 10 11 12
         *
         * 结果分析：
         */
    }
}
