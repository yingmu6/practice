package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class EnumOrder { //@TkY-Doing

    /**
     * 知识点（5.9）：
     *
     * 知识点概括：
     * 1）
     */

    public static void main(String[] args) {
        for (Spiciness s : Spiciness.values()) {
            System.out.println(s + "，ordinal " + s.ordinal());
        }

        /**
         * 输出结果：
         * fiddle
         * de
         * dum
         *
         * 结果分析：
         * 1）枚举中编译器会添加values()方法获取到所有枚举值
         */
    }
}
