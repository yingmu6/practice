package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class SimpleConstructor2 {

    /**
     * 知识点（5.1）：
     */
    static class Rock2 {
        Rock2(int i) {
            System.out.print("Rock " + i + " ");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            new Rock2(i);
        }
    }
}
