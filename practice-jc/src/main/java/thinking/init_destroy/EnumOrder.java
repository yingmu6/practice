package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class EnumOrder {

    /**
     * 知识点（5.9）：
     */

    public static void main(String[] args) {
        for (Spiciness s : Spiciness.values()) {
            System.out.println(s + "，ordinal " + s.ordinal());
        }
    }
}
