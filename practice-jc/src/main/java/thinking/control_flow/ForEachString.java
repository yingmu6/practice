package thinking.control_flow;

/**
 * @author orange
 * @date 2024/6/3
 */
public class ForEachString {

    /**
     * 知识点（4.4）：Foreach语法
     */
    public static void main(String[] args) {
        for (char c : "An African Swallow".toCharArray()) {
            System.out.println(c + " ");
        }
    }
}
