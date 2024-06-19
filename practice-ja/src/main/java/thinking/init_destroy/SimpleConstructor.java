package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class SimpleConstructor {

    /**
     * 知识点（5.1）：用构造器确保初始化
     */

    static class Rock {
        Rock() {
            System.out.println("Rock ");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Rock();
        }
    }
}
