package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class OptionalTrailingArguments {

    /**
     * 知识点（5.8.1）：
     */
    static void f(int required, String... trailing) {
        System.out.print("required：" + required + " ");
        for (String s: trailing) {
            System.out.println(s + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        f(1, "one");
        f(2, "two", "three");
        f(0);
    }
}
