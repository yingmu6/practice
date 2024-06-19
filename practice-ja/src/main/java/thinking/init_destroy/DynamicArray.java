package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class DynamicArray { //@TkY-Doing

    /**
     * 知识点（5.8）：
     *
     * 知识点概括：
     * 1）
     */
    static class Other {
        public static void main(String[] args) {
           for (String s : args) {
               System.out.println(s + " ");
           }
        }
    }

    public static void main(String[] args) {
        Other.main(new String[] {"fiddle", "de", "dum"});

        /**
         * 输出结果：
         * fiddle
         * de
         * dum
         *
         * 结果分析：
         * 1）调用类中的main方法，并传入参数值
         */
    }
}
