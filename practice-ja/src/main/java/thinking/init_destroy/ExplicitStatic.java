package thinking.init_destroy;

import org.junit.Test;

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

    public static void main(String[] args) { //Done
        print("Inside main()");
        Cups.cup1.f(99);

        /**
         * 输出结果：
         * Inside main()
         * Cup(1)
         * Cup(2)
         * f(99)
         *
         * 结果分析：
         */
    }

    /**
     * 新增场景：
     */
    @Test
    public void scene2() { //Done
        new Cups();

        /**
         * 输出结果：
         * Cup(1)
         * Cup(2)
         * Cups()
         *
         * 结果分析：
         * 1）new创建对象时，会初始化静态变量、静态块，还会调用构造方法
         */
    }
}
