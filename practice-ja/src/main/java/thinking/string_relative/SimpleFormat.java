package thinking.string_relative;

/**
 * @author orange
 * @date 2024/6/4
 */
public class SimpleFormat {

    /**
     * 知识点（13.5）：格式化输出
     */

    public static void main(String[] args) {
        int x = 5;
        double y = 5.332542;
        System.out.println("Row 1：[" + x + " " + y + "]");
        System.out.format("Row 1：[%d %f]\n", x, y);
        System.out.printf("Row 1：[%d %f]\n", x, y);
    }
}
