package com.csy.interview.fourth_book.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class PrintTest {

    /**
     * 场景：从键盘输入两个整数，然后输出它们的平方值及立方值
     */
    public static void main(String[] args) {
        Result result = new Result();
        System.out.println("请输入一个整数：");
        int a = InputData.getInt();
        result.print(a);
    }

    static class InputData {
        private static String s = "";
        static public void input() {
            BufferedReader bu = new BufferedReader(new InputStreamReader(System.in));
            try {
                s = bu.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static public int getInt() {
            input();
            return Integer.parseInt(s);
        }
    }

    static class Result {
        static void print(int d) {
            System.out.println(d + "的平方：" + d * d);
            System.out.println(d+ "的立方" + d * d * d);
        }
    }
}
