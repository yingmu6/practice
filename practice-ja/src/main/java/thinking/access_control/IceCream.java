package thinking.access_control;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class IceCream {

    /**
     * 知识点（6.2.3）：private 你无法访问
     */
    public static void main(String[] args) {
        // Sundae x = new Sundae(); //因为Sundae的构造方法是private，所以不能访问
        Sundae y = Sundae.makeASumdae();
    }
}
