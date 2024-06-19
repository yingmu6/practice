package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/11
 */
public class TernaryIfElse {

    /**
     * 知识点（3.12）：三元操作符if-else
     */
    static int ternary(int i) {
        return i < 10 ? i * 100 : i * 10;
    }

    static int standardIfElse(int i) {
        if (i < 10) {
            return i * 100;
        } else {
            return i * 10;
        }
    }

    public static void main(String[] args) {
        print(ternary(9));
        print(ternary(10));
        print(standardIfElse(9));
        print(standardIfElse(10));
    }
}
