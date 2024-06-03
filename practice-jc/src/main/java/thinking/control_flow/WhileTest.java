package thinking.control_flow;

/**
 * @author orange
 * @date 2024/6/3
 */
public class WhileTest {

    /**
     * 知识点（4.3）：迭代
     *
     * 知识点概括：
     * 1）while循环格式：
     *         while(Boolean-expression)
     *            statement
     */
    static boolean condition() {
        boolean result = Math.random() < 0.99;
        System.out.println(result + "，");
        return result;
    }

    public static void main(String[] args) {
        while (condition()) {
            System.out.println("Inside 'while' ");
        }
        System.out.println("Exited 'while' ");
    }
}
