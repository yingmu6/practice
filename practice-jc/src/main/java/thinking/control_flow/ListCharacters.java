package thinking.control_flow;

/**
 * @author orange
 * @date 2024/6/3
 */
public class ListCharacters {

    /**
     * 知识点（4.3.2）：for
     *
     * 知识点概括：
     * 1）for循环格式如下：
     *         for(initialization; Boolean-expression; step)
     *            statement
     */
    public static void main(String[] args) {
        for (char c = 0; c < 128; c++) {
            if(Character.isLowerCase(c)) {
                System.out.println("value：" + (int)c + " character：" + c);
            }
        }
    }
}
