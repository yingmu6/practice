package thinking.control_flow;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/3
 */
public class IfElse2 {

    /**
     * 知识点（4.5）：return
     */
    static int test(int testVal, int target) {
        if (testVal > target) {
            return +1;
        } else if (testVal < target) {
            return -1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        print(test(10, 5));
        print(test(5, 10));
        print(test(5, 5));
    }
}
