package thinking.string_relative;

/**
 * @author orange
 * @date 2024/6/4
 */
public class IntegerMatch {

    /**
     * 知识点（13.6）：正则表达式
     */
    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d"));
    }
}
