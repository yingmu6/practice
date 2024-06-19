package thinking.operator;

/**
 * @author orange
 * @date 2024/6/7
 */
public class Precedence {

    /**
     * 知识点（3.3）：优先级
     */
    public static void main(String[] args) {
        int x = 1, y = 2, z = 3;
        int a = x + y - 2/2 + z;
        int b = x + (y - 2) / (2 + z);
        System.out.println("a = " + a + " b = " + b);
    }
}
