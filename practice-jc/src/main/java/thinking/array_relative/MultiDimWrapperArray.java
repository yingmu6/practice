package thinking.array_relative;

import java.util.Arrays;

/**
 * @author orange
 * @date 2024/5/28
 */
public class MultiDimWrapperArray {

    /**
     * 知识点（16.4）：
     */

    public static void main(String[] args) {
        Integer[][] a1 = {
                { 1, 2, 3, },
                { 4, 5, 6, }
        };

        Double[][][] a2 = {
                { { 1.1, 2.2 }, { 3.3, 4.4 } },
                { { 5.5, 6.6 }, { 7.7, 8.8 } },
                { { 9.9, 1.2 }, { 2.3, 3.4 } }
        };

        String[][] a3 = {
                { "The", "Quick", "Sly", "Fox" },
                { "Jumped", "Over" },
                { "The", "Lazy", "Brown", "Dog", "and", "friend" },
        };
        System.out.println("a1：" + Arrays.deepToString(a1));
        System.out.println("a2：" + Arrays.deepToString(a2));
        System.out.println("a3：" + Arrays.deepToString(a3));
    }
}
