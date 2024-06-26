package thinking.init_destroy;

import java.util.Arrays;
import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class ArrayNew { //@TkY-Doing

    /**
     * 知识点：使用new创建数组
     */

    public static void main(String[] args) {
        int[] a;
        Random rand = new Random(47);
        a = new int[rand.nextInt(20)];
        print("length of a = " + a.length);
        print(Arrays.toString(a));

        /**
         * 输出结果：
         * length of a = 18
         * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
         *
         * 结果分析：
         * 1）使用new方式创建数组
         *
         * 问题点答疑：
         * 1）此处rand.nextInt(20)为什么多次运行都是显示18的值？
         */
    }
}
