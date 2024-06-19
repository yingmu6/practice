package thinking.init_destroy;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class OverloadingOrder {

    /**
     * 知识点（5.2.1）：区分重载方法
     */
    static void f(String s, int i) {
        print("String：" + s + "，int：" + i);
    }

    static void f(int i, String s) {
        print("int：" + i + "，String：" + s);
    }

    public static void main(String[] args) {
        f("String first", 11);
        f(99, "Int first");
    }
}
