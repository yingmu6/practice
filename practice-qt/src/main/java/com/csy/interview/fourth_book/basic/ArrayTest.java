package com.csy.interview.fourth_book.basic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author chensy
 * @date 2023/7/13
 */
public class ArrayTest { //@MsY-Done

    /**
     * 知识点：数组
     *
     * 知识点概要：
     * 1）数组的声明：
     *    1.1）数组声明的括号中不能显示行数、列数，如 char[1][2];
     *    1.2）数组创建的时，括号中需要指定行数、列数，如char[][] c = new char[1][2];
     *
     * 2）数组的排序：
     *    2.1）Arrays.sort() 方法对对象或原始数据类型的数组进行排序。它是快速排序算法的自定义实现，以实现更好的性能。
     *    2.2）Arrays.parallelSort() 在功能上有所不同。与 sort() 使用单个线程对数据进行顺序排序不同，它使用并行排序-合并排序算法。
     *         它将数组分成子数组，这些子数组本身先进行排序然后合并。即使用的是ForkJoinTask并发处理任务。
     *    2.3）Arrays.parallelSort() 只有在满足某些条件时，它才会使用并行性。如果数组大小小于或等于 8192，或者处理器只有一个核心，
     *         则它将使用顺序的Dual-Pivot Quicksort算法。否则，它使用并行排序。
     *
     * 3）数组的拷贝：
     *    3.1）可以循环遍历原有数组，依次取出元素然后再设置到新的数组中
     *    3.2）使用System.arrayCopy可以从原数组指定位置，拷贝指定数量的元素到目标数组的目标位置上
     *    3.3）使用System.arrayCopy实现数组的新增、删除逻辑
     *
     * 参考链接：
     * a）https://blog.csdn.net/qq_20971061/article/details/106602442 Arrays.sort与Arrays.parallelSort区别
     */

    /**
     * 场景1：数组声明
     */
    @Test
    public void test_declare() {
        char c[][] = new char[1][2];
        String []s;

        /**
         * 数组不能直接指定，行数和列数，需要在创建数组对象时指定，例如：
         * int iArray[][] = new int[3][4]
         */
        // String s2[50]; //语法错误，报出 ']' expected
        // Object s3[50]; //语法错误，报出 ']' expected
    }

    /**
     * 场景2：并行数组测试
     */
    @Test
    public void test_parallel_array() {
        int []arr = {1,5,8,3,19,40,6};
        Arrays.parallelSort(arr);
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
        System.out.println();

        /**
         * 输出结果：
         * 1 3 5 6 8 19 40
         *
         * 结果分析：
         * parallelSort按升序对arr排序
         */
    }

    /**
     * 场景3：使用System.arrayCopy进行数组拷贝
     */
    @Test
    public void test_system_array_copy() {
        int []arr = new int[] {1, 3, 5, 7};

        int []arr2 = new int[arr.length - 2];

        System.arraycopy(arr, 0, arr2, 0, 2);

        String str1 = printArray(arr2);
        System.out.println("拷贝后的数组：" + str1);

        /**
         * 输出结果：
         * 拷贝后的数组：1,3,0,0
         *
         * 结果分析：
         * 1）使用System.arrayCopy可以从原数组指定位置，拷贝指定数量的元素到目标数组的目标位置上
         */
    }

    /**
     * 场景4：使用System.arrayCopy实现数组的新增、删除逻辑
     */
    @Test
    public void test_system_array_copy_for_add_or_remove() {
        int []arr = new int[] {1, 3, 5, 7};

        // 在下标为2添加元素8
        int []temp = add(arr, 2, 8);
        String str1 = printArray(temp);
        System.out.println("添加元素后的数组：" + str1);

        /**
         * 输出结果：
         * 添加元素后的数组：1,3,8,5,7,0,0,0
         *
         * 结果分析：
         * 1）在数组指定下标添加元素时，分为两部分处理，index前、后，依次使用System.arraycopy即可
         */

        // 移除下标为3的元素
        remove(arr, 2);
        String str2 = printArray(arr);
        System.out.println("移除元素后的数组：" + str2);

        /**
         * 输出结果：
         * 移除元素后的数组：1,5,7,0
         *
         * 结果分析：
         * 移除指定位置的元素，则位置后面的元素通过System.arraycopy整体往前移动一位，因为移除不需要扩容，所以可在原有数组执行即可。
         */

    }

    private int[] add(int []source, int index, int value) {
        int []target = new int[source.length * 2]; //增加元素时，需要扩容

        System.arraycopy(source, 0, target, 0, index);
        target[index] = value;
        System.arraycopy(source, index, target, index + 1, source.length - index);
        return target;
    }

    private void remove(int []source, int index) {
        System.arraycopy(source, index, source, index -1, source.length - index); //整体向前移动一位
        source[source.length - 1] = 0; //末尾数清零
    }

    private String printArray(int []arr) {
        String str1 = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                str1 = str1 + "," + arr[i];
            } else {
                if (i == 0) {
                    str1 = str1 + arr[i];
                } else {
                    str1 = str1 + "," + arr[i];
                }
            }
        }
        return str1;
    }
}
