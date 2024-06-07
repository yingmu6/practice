package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/7
 */
public class PassObject {

    /**
     * 知识点（3.4.1）：方法调用中的别名问题
     */
    static class Letter {
        char c;
    }

    static void f(Letter y) {
        y.c = 'z';
    }

    public static void main(String[] args) {
        Letter x = new Letter();
        x.c = 'a';
        print("1：x.c：" + x.c);
        f(x);
        print("2：x.c：" + x.c);
    }

}
