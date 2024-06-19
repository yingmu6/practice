package thinking.control_flow;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/3
 */
public class IfElse {

    /**
     * 知识点（4.1）：if-else语句
     *
     * 知识点概括：
     * 1）if-else语句格式：
     *           if(Boolean-expression)
     *             statement
     *           或
     *           if(Boolean-expression)
     *             statement
     *           else
     *             statement
     *           或
     *           if(Boolean-expression)
     *             statement
     *           else if(Boolean-expression)
     *             statement
     *           else
     *             statement
     */
    static int result = 0;
    static void test(int testVal, int target) {
        if (testVal > target) {
            result = +1;
        } else if (testVal < target) {
            result = -1;
        } else {
            result = 0;
        }
    }

    public static void main(String[] args) {
        test(10, 5);
        print(result);
        test(5, 10);
        print(result);
        test(5, 5);
        print(result);
    }
}
