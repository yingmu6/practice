package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class OverloadingVarargs3 {

    /**
     * 知识点（5.8.1）：
     */

    static void f(float i, Character... args) {
        System.out.println("first");
    }

    static void f(char c, Character... args) {
        System.out.println("second");
    }

    public static void main(String[] args) {
        f(1, 'a');
        f('a', 'b');
    }
}
