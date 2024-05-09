package thinking.access_control;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class Cake {

    /**
     * 知识点（6.2.2）：默认包
     */
    public static void main(String[] args) {
        Pie x = new Pie();
        x.f(); //此处Cake与Pie同在一个包下，所以可以访问Pie的默认方法

        /**
         * 输出结果：
         * Pie.f()
         */
    }
}
