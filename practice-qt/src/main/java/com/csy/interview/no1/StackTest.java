package com.csy.interview.no1;

import org.junit.Test;

import java.util.Stack;
import java.util.Vector;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class StackTest {

    /**
     * 模拟_栈操作
     */

    /**
     * 题目描述：
     * 实现一个栈操作，将1~12月的英文单词压入栈中，然后将其取出
     */

    String[] months = {
            "January","February","March","April",
            "May","June","July","August",
            "September","October","November","December"
    };

    /**
     * 场景1：使用Stack实现
     */
    @Test
    public void test_by_stack() {
        Stack stk = new Stack();
        for (int i = 0; i < months.length; i++) {
            stk.push(months[i] + ""); //入栈
        }
        System.out.println("stk = " + stk);
        stk.addElement("The last line"); //添加栈元素
        System.out.println("element 5 = " + stk.elementAt(5)); //取栈中指定位置的元素
        System.out.println("popping elements:");
        while (!stk.empty()) {
            System.out.println(stk.pop()); //出栈
        }

        /**
         * 输出结果：
         * stk = [January, February, March, April, May, June, July, August, September, October, November, December]
         * element 5 = June
         * popping elements:
         * The last line
         * December
         * November
         * October
         * September
         * August
         * July
         * June
         * May
         * April
         * March
         * February
         * January
         *
         *
         * 结果分析：
         * 1）Stack的push()、addElement()都能添加元素
         * 2）Stack栈是后进先出
         */
    }

    /**
     * 场景2：使用队列实现
     */
    @Test
    public void test_by_queue() {
        Vector vq = new Vector();
        for (int i = 0; i < months.length; i++) {
            vq.addElement(months[i] + "");
        }

        if (vq.isEmpty()) {
            System.out.println("kong");
        }

        while (!vq.isEmpty()) {
            System.out.println(vq.lastElement());
            vq.removeElement(vq.lastElement());
        }

        vq.clear();

        /**
         * 输出结果：
         * December
         * November
         * October
         * September
         * August
         * July
         * June
         * May
         * April
         * March
         * February
         * January
         *
         * 结果分析：
         * 使用队列来模拟栈功能，因为队列是先进先出，所以要实现后进先出，就需要从最后元素开始取值
         *
         */
    }
}
