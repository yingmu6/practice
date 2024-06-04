package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class DefaultConstructor { //@TkY-Done

    /**
     * 知识点（5.3）：默认构造器
     *
     * 相关点学习：
     * 1）用javap看下默认构造方法
     */

    static class Bird {}

    public static void main(String[] args) {
        Bird b = new Bird();

        /**
         * 结果分析：
         * 1）Bird中没有声明构造器，编译器会自动加上默认构造方法
         */
    }
}
