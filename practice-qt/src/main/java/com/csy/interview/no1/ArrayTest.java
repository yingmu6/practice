package com.csy.interview.no1;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author chensy
 * @date 2023/7/13
 */
public class ArrayTest {

    /**
     * 数组_测试
     */

    /**
     * 场景1：数组声明
     */
    @Test
    public void test_declare() {
        char c[][];
        String []s;

        /**
         * 数组不能直接指定，行数和列表，需要在创建数组对象时指定，例如：
         * int iArray[][] = new int[3][4]
         */
        // String s2[50]; //语法错误，报出 ']' expected
        // Object s3[50]; //语法错误，报出 ']' expected
    }

    /**
     * 场景2：并行数组测试
     * 1）Arrays.sort() 方法对对象或原始数据类型的数组进行排序。它是快速排序算法的自定义实现，以实现更好的性能。
     *
     * 2）parallelSort() 在功能上有所不同。与 sort() 使用单个线程对数据进行顺序排序不同，它使用并行排序-合并排序算法。
     *   它将数组分成子数组，这些子数组本身先进行排序然后合并。
     *
     * 3）只有在满足某些条件时，它才会使用并行性。如果数组大小小于或等于 8192，或者处理器只有一个核心，
     *    则它将使用顺序的 Dual-Pivot Quicksort 算法。否则，它使用并行排序。
     *
     * 参考链接：
     * a）https://blog.csdn.net/qq_20971061/article/details/106602442 Arrays.sort与Arrays.parallelSort区别
     */
    @Test
    public void test_parallel_array() {
        int []arr = {1,5,8,3,19,40,6};
        Arrays.parallelSort(arr);
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    /**
     * 输出结果：
     * 1 3 5 6 8 19 40
     *
     * 结果分析：
     * parallelSort按升序对arr排序
     */
}
