package thinking.init_destroy;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class OrderOfInitialization {

    /**
     * 知识点：构造器初始化
     */

    static class Window {
        Window(int marker) { print("Window(" + marker + ")"); }
    }

    static class House {
        Window w1 = new Window(1);
        House() {
            print("House()");
            w3 = new Window(33);
        }
        Window w2 = new Window(2);
        void f() { print("f()"); }
        Window w3 = new Window(3);
    }

    public static void main(String[] args) {
        House h = new House();
        h.f();

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }
}
