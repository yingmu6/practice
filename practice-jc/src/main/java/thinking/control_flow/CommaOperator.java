package thinking.control_flow;

/**
 * @author orange
 * @date 2024/6/3
 */
public class CommaOperator {

    /**
     * 知识点（4.3.3）：逗号操作符
     */
    public static void main(String[] args) {
        for(int i = 1, j = i + 10; i < 5; i++, j = i * 2) {
            System.out.println("i = " + i + "j = " + j);
        }
    }
}
