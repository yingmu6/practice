package com.csy.algorithm.recurtion;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 递归算法
 * @author : chensy
 * Date : 2020/7/27 下午5:34
 */
public class RecursionTest {

    /**
     * 递归算法_概述
     * 1）The process in which a function calls itself directly or
     *   indirectly is called recursion and the corresponding（相应的） function is called a recursive function
     *  （函数直接或间接调用自身的过程间接称为递归，对应的函数称为递归函数）
     *
     * 2）Properties of Recursion:（递归的性质）
     *   a）Performing the same operations multiple times with different inputs.（使用不同的输入多次执行相同的操作。）
     *   b）In every step, we try smaller inputs to make the problem smaller.（在每一步中，我们尝试更小的输入来使问题更小）
     *   c）Base condition is needed to stop the recursion otherwise infinite loop will occur.（需要基本条件来停止递归，否则将发生无限循环）
     *
     * 3）Recursion uses more memory, because the recursive function adds to the stack with each recursive call,
     *   and keeps the values there until the call is finished.
     *   The recursive function uses LIFO (LAST IN FIRST OUT) Structure just like the stack data structure.
     *  （递归会使用更多的内存，每次递归函数调用都会添加到栈中，会保持值直到调用完成。递归函数使用类似栈的数据接口，实现LIFO 后进先出）
     *
     * 4）递归函数的设计思想：分而治之
     * 分而治之（Divide-and-Conquer）的思想分为如下三步：
     *   a）拆分：将原问题拆分成若干个子问题；
     *   b）解决：解决这些子问题；
     *   c）合并：合并子问题的解得到原问题的解
     *
     * 递归算法_总结
     * 1）需要有递归结束条件，不然出现无限循环
     * 2）递归适合能将问题拆分为小问题，小问题按同样的解题逻辑来实现
     *
     * 参考链接：
     * 1）https://www.geeksforgeeks.org/introduction-to-recursion-data-structure-and-algorithm-tutorials
     */

    /**
     * 场景1：列举字符数组中所有字符的组合情况
     * 1）题目内容：把一个数组里的数组合全部列出
     *       输入：字符数组为["1", "2"]
     *       输出："1"，"2"，"12"，"21"
     *
     * 2）解题思路：
     */
    @Test
    public void test_basic_use() {
        String[] array = new String[]{
          "1", "2"
        };
        listAll(Arrays.asList(array), "");
    }

    private void listAll(List<String> list, String prefix) {
        System.out.println(prefix);

        for (int i = 0; i < list.size(); i++) {
            List<String> temp = new LinkedList(list);
            listAll(temp, prefix + temp.remove(i));
        }
    }

    /**
     * 场景2：斐波那契数列计算
     * 1）题目内容：计算斐波那契数列的通项f(n)，已知f1=1，f2=1，以后每项都是前两项的和
     *       输入：f3=f1+f2，f4=f3+f2，f5=f4+f3
     *       输出：f3=2，f4=3，f5=5
     * 2）解题思路：
     */

    @Test
    public void test_fibonacci() {
//        Scanner cin = new Scanner(System.in); //@Test接收不了System.in控制台输入的值（调用方式时，直接指定值即可）
//        long a= cin.nextLong();
//        System.out.println(fibonacci(a));

        System.out.println(fibonacci(9)); //输出34
        System.out.println(fibonacci(10));//输出55
        System.out.println("共递归调用了" + k + "次");
    }

    private static int k = 0;
    private long fibonacci(long m) {
        if (m == 0 || m == 1) {
            k++;
            return m;
        } else {
            return fibonacci(m - 1) + fibonacci(m - 2);
        }
    }
}
