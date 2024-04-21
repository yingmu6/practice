package thinking.init_destroy;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class Mugs {

    /**
     * 知识点：非静态实例初始化
     */
    static class Mug {
        Mug(int marker) {
            print("Mug(" + marker + ")");
        }
        void f(int marker) {
            print("f(" + marker + ")");
        }
    }

    Mug mug1;
    Mug mug2;
    {
        mug1 = new Mug(1);
        mug2 = new Mug(2);
        print("mug1 & mug2 initialized");
    }

    Mugs() {
        print("Mugs()");
    }

    Mugs(int i) {
        print("Mugs(int)");
    }

    public static void main(String[] args) {
        print("Inside main()");
        new Mugs();
        print("new Mugs() completed");
        new Mugs(1);
        print("new Mugs(1) completed");
    }
}
