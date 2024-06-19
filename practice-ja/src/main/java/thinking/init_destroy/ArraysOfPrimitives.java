package thinking.init_destroy;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class ArraysOfPrimitives { //@TkY-Done

    /**
     * 知识点：数组初始化
     */

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2;
        a2 = a1; //两个数组对象，有相同的内存地址
        for (int i = 0; i < a2.length; i++) {
            a2[i] = a2[i] + 1;
        }

        for (int i = 0; i < a1.length; i++) {
            print("a1[" + i + "]=" + a1[i]);
        }

        /**
         * 输出结果：
         * a1[0]=2
         * a1[1]=3
         * a1[2]=4
         * a1[3]=5
         * a1[4]=6
         *
         * 结果分析：
         * 1）由数组对象进行赋值a2 = a1，即指向相同的地址，所以a2数组元素的改变，影响a1数组元素
         */
    }
}
