package thinking.generic_type;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class GenericMethods {

    /**
     * 知识点（15.4）：泛型方法
     */
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}
