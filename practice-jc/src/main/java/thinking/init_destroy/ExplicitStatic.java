package thinking.init_destroy;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class ExplicitStatic {

    /**
     * 知识点：显示的静态初始化
     */

    static class Cup {
        Cup(int marker) {
            print("Cup(" + marker + ")");
        }
        void f(int marker) {
            print("f(" + marker + ")");
        }
    }

    static class Cups {
        static Cup cup1;
        static Cup cup2;
        static {
            cup1 = new Cup(1);
            cup2 = new Cup(2);
        }
        Cups() {
            print("Cups()");
        }
    }

    public static void main(String[] args) {
        print("Inside main()");
        Cups.cup1.f(99);
    }
}
