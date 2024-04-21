package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class VarargType {

    /**
     * 知识点（5.8.1）：
     */

    static void f(Character... args) {
        System.out.print(args.getClass());
        System.out.println(" length " + args.length);
    }

    static void g(int... args) {
        System.out.println(args.getClass());
        System.out.println(" length " + args.length);
    }

    public static void main(String[] args) {
        f('a');
        f();
        g(1);
        g();
        System.out.println("int[]：" + new int[10].getClass());
    }
}
