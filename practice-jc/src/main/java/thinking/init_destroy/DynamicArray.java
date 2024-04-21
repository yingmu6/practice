package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class DynamicArray {

    /**
     * 知识点（5.8）：
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
    }
}
