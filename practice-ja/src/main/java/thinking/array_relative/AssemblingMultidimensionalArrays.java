package thinking.array_relative;

import java.util.Arrays;

/**
 * @author orange
 * @date 2024/5/28
 */
public class AssemblingMultidimensionalArrays {

    /**
     * 知识点（16.2）：
     */

    public static void main(String[] args) {
        Integer[][] a;
        a = new Integer[3][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Integer[3];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = i * j;
            }
        }
        System.out.println(Arrays.deepToString(a));
    }
}
