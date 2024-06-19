package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class NoSynthesis {

    /**
     * 知识点（5.3）：
     */

    static class Bird2 {
        Bird2(int i) {}
        Bird2(double d) {}
    }

    public static void main(String[] args) {
        Bird2 b2 = new Bird2(1);
        Bird2 b3 = new Bird2(1.0);
    }
}
