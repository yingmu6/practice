package thinking.holder_obj;

import java.util.*;

/**
 * @author orange
 * @date 2024/6/5
 */
public class ModifyArrayAsList { //@TkY-Doing

    /**
     * 知识点（11.13）
     */
    public static void main(String[] args) { //Doing
        Random rand = new Random(47);
        Integer[] ia = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list1 =
                new ArrayList<>(Arrays.asList(ia));
        System.out.println("Before shuffling：" + list1);
        Collections.shuffle(list1, rand); //重新排列
        System.out.println("After shuffling：" + list1);
        System.out.println("array：" + Arrays.toString(ia));

        List<Integer> list2 = Arrays.asList(ia);
        System.out.println("Before shuffing: " + list2);
        Collections.shuffle(list2, rand);
        System.out.println("After shuffing：" + list2);
        System.out.println("array：" + Arrays.toString(ia));

        /**
         * 输出结果：
         * Before shuffling：[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         * After shuffling：[4, 6, 3, 1, 8, 7, 2, 5, 10, 9]
         * array：[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         * Before shuffing: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
         * After shuffing：[9, 1, 6, 3, 7, 2, 5, 10, 4, 8]
         * array：[9, 1, 6, 3, 7, 2, 5, 10, 4, 8]
         *
         * 结果分析：
         * 1）第一次shuffle排列后，不影响原数组ia的序列；第二次shuffle排列后原数组ia的序列变了
         *
         * 问题点答疑：
         * 1）为什么第二次shuffle后，会影响原数组ia的序列
         */
    }
}
