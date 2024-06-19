package thinking.access_control;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class PrintTest {

    /**
     * 知识点（6.1.3）：定制工具类
     */
    public static void main(String[] args) {
        print("Available from now on!");
        print(100);
        print(100L);
        print(3.14159);

        /**
         * 结果输出：
         * Available from now on!
         * 100
         * 100
         * 3.14159
         */
    }
}
