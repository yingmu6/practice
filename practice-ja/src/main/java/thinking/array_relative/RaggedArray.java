package thinking.array_relative;

import java.util.Arrays;
import java.util.Random;

/**
 * @author orange
 * @date 2024/5/28
 */
public class RaggedArray {

    /**
     * 知识点（16.4）：
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        int[][][] a = new int[rand.nextInt(7)][][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new int[rand.nextInt(5)][];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = new int[rand.nextInt(5)];
            }
        }
        System.out.println(Arrays.deepToString(a));
    }
}
