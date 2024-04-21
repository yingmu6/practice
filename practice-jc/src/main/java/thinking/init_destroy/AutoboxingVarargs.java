package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class AutoboxingVarargs {

    /**
     * 知识点（5.8.1）：
     */

    public static void f(Integer... args) {
        for (Integer i : args) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        f(new Integer(1), new Integer(2));
        f(4, 5, 7, 8, 9);
        f(10, new Integer(11), 12);
    }
}
