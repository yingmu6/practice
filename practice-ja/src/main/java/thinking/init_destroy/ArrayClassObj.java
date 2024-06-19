package thinking.init_destroy;

import java.util.Arrays;
import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class ArrayClassObj { //@TkY-Doing

    /**
     * 知识点：
     *
     * 知识点概要：
     * 1）
     */

    public static void main(String[] args) {
        Random rand = new Random(47);
        Integer[] a = new Integer[rand.nextInt(20)];
        print("length of a = " + a.length);
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(500);
        }
        print(Arrays.toString(a));

        /**
         * 输出结果：
         * length of a = 18
         * [55, 193, 361, 461, 429, 368, 200, 22, 207, 288, 128, 51, 89, 309, 278, 498, 361, 20]
         *
         * 结果分析：
         * 1）使用产生的随机值，依次对数组元素进行初始化。
         */
    }
}
