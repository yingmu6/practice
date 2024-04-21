package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class NewVarArgs {

    /**
     * 知识点（5.8.1）：
     */

    static void printArray(Object... args) {
        for (Object obj : args) {
            System.out.println(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArray(new Integer(47), new Float(3.14), new Double(11.11));
        printArray(47, 3.14F, 11.11);
        printArray("one", "two", "three");
        printArray(new VarArgs.A(), new VarArgs.A(), new VarArgs.A() );
        printArray((Object[]) new Integer[]{1, 2, 3, 4});
        printArray();
    }
}
