package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class BananaPeel { //@TkY-Doing

    /**
     * 知识点：
     *
     * 问题点：
     * 1）用例的本意是什么？
     */

    static class Banana {
        void peel(int i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Banana a = new Banana(),
               b = new Banana();
        a.peel(1);
        b.peel(2);

        /**
         * 输出结果：
         * 1
         * 2
         *
         * 结果分析：
         * 1）调用静态内部类Banana中的方法
         */
    }
}
