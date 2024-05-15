package thinking.combinate_class;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class BlankFinal { //@TkY-Doing

    /**
     * 知识点（7.8）：空白final
     *
     * 问题点：
     * 1）为啥叫空白final，本意是什么？
     */

    class Poppet {
        private int i;
        Poppet(int i) { i = 11; }
    }

    private final int i = 0;
    private final int j;
    private final Poppet p;
    public BlankFinal() {
        j = 1;
        p = new Poppet(1);
    }

    public BlankFinal(int x) {
        j = x;
        p = new Poppet(x);
    }

    public static void main(String[] args) {
        new BlankFinal();
        new BlankFinal(47);

        /**
         * 运行结果：
         * （没结果输出）
         *
         * 结果分析：
         */
    }
}
