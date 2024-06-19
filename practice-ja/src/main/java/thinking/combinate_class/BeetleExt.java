package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class BeetleExt {

    /**
     * 知识点（7.9.1）：继承和初始化
     */
    static class Insect {
        private int i = 9;
        protected int j;
        Insect() {
            print("i = " + i + "，j = " + j);
            j = 39;
        }

        private static int x1 =
                printInit("static Insect.x1 initalized");

        static int printInit(String s) {
            print(s);
            return 47;
        }
    }

    static public class Beetle extends Insect {
        private int k = printInit("Beetle.k initialized");
        public Beetle() {
            print("k = " + k);
            print("j = " + j);
        }

        private static int x2 =
                printInit("static Beetle.x2 initialized");

        public static void main(String[] args) {
            print("Beetle constructor");
            Beetle b = new Beetle();
        }
    }
}
