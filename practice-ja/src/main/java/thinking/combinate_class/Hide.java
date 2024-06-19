package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class Hide {

    /**
     * 知识点（7.4.2）：名称隐蔽
     */

    static class Homer {
        char doh(char c) {
            print("doh(char)");
            return 'd';
        }

        float doh(float f) {
            print("doh(float)");
            return 1.0f;
        }
    }

    static class Milhouse {}

    static class Bart extends Homer {
        void doh(Milhouse m) {
            print("doh(Milhouse)");
        }
    }

    public static void main(String[] args) {
        Bart b = new Bart();
        b.doh(1);
        b.doh('x');
        b.doh(1.0f);
        b.doh(new Milhouse());
    }
}
