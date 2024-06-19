package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/11
 */
public class StringOperators {

    /**
     * 知识点（3.13）：字符串操作符 + 和 +=
     */
    public static void main(String[] args) {
        int x = 0, y = 1, z = 2;
        String s = "x, y, z";
        print(s + x + y + z);
        print(x + " " + s);
        s += "(summed) = ";
        print(s + (x + y + z));
        print("" + x);
    }
}
