package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class CartoonExt {

    /**
     * 知识点（7.2.1）：初始化基类
     */

    static class Art {
        Art() { print("Art constructor"); }
    }

    static class Drawing extends Art {
        Drawing() { print("Drawing constructor"); }
    }

    static public class Cartoon extends Drawing {
        public Cartoon() { print("Cartoon constructor"); }

        public static void main(String[] args) {
            Cartoon x = new Cartoon();
        }
    }
}
