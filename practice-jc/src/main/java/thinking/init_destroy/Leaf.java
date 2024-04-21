package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class Leaf {

    /**
     * 知识点（5.4）：this关键字
     */

    int i = 0;

    Leaf increment() {
        i++;
        return this;
    }

    void print() {
        System.out.println("i = " + i);
    }

    public static void main(String[] args) {
        Leaf x = new Leaf();
        x.increment().increment().increment().print();

        /**
         * 输出结果：
         *
         * 结果分析：
         * 1）increment()通过this关键字返回了对当前对象的引用，就很容易在一条语句中对同一个对象执行多次操作。
         *
         */
    }
}
