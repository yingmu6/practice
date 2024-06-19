package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/8
 */
public class ShortCircut {

    /**
     * 知识点（3.8.1）：短路
     */
    static boolean test1(int val) {
        print("test1(" + val + ")");
        print("result：" + (val < 1));
        return val < 1;
    }

    static boolean test2(int val) {
        print("test2(" + val + ")");
        print("result：" + (val < 2));
        return val < 2;
    }

    static boolean test3(int val) {
        print("test3(" + val + ")");
        print("result：" + (val < 3));
        return val < 3;
    }

    public static void main(String[] args) {
        boolean b = test1(0) && test2(2) && test3(2);
        print("expression is " + b);
    }
}
