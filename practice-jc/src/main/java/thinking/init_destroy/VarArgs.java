package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class VarArgs {

    /**
     * 知识点（5.8.1）：可变参数列表
     */

    static class A {}

    static void printArray(Object[] args) {
        for (Object obj : args) {
            System.out.println(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArray(new Object[]{
                new Integer(47), new Float(3.14), new Double(11.11)
        });
        printArray(new Object[]{"one", "two", "three"});
        printArray(new Object[]{new A(), new A(), new A()});
    }
}
