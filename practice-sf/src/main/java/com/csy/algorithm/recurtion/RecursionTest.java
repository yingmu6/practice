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
     *
     */

    /**
     * 场景1：列举字符数组中所有字符的组合情况
     * 1）题目内容：把一个数组里的数组合全部列出
     *       输入：字符数组为["1", "2"]
     *       输出："1"，"2"，"12"，"21"
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

//    public static void main(String[] args) {
//        Scanner cin = new Scanner(System.in);
//        long a= cin.nextLong();
//        System.out.println(fibonacci(a));
//        System.out.println("共递归调用了" + k + "次");
//    }
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
