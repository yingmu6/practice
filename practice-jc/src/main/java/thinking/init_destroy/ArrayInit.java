package thinking.init_destroy;

import java.util.Arrays;

/**
 *
 * @author chensy
 * @date 2024/4/19
 */
public class ArrayInit {

    /**
     * 知识点（5.8）：
     */
    public static void main(String[] args) {
        Integer[] a = {
                new Integer(1),
                new Integer(2),
                3,
        };

        Integer[] b = {
                new Integer(1),
                new Integer(2),
                3,
        };

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
