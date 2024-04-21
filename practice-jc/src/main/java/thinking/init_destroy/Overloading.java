package thinking.init_destroy;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class Overloading {

    /**
     * 知识点（5.2）：
     */

    static class Tree {
        int height;
        Tree() {
            print("Planting a seeding");
            height = 0;
        }
        Tree(int initialHeight) {
            height = initialHeight;
            print("Creating new Tree that is " +
                    height + " feet tall ");
        }
        void info() {
            print("Tree is " + height + " feet tall ");
        }
        void info(String s) {
            print(s + "：Tree is " + height + " feel tall ");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Tree t = new Tree(i);
            t.info();
            t.info("overloaded method");
        }
        new Tree();
    }
}
