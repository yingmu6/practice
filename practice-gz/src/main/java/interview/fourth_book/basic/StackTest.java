package interview.fourth_book.basic;

import org.junit.Test;

import java.util.Stack;
import java.util.Vector;

/**
 * @author chensy
 * @date 2023/7/7
 */
public class StackTest { //@MsY-Doing

    /**
     * 知识点：模拟栈操作（后进先出）
     *
     * 题目描述：
     * 实现一个栈操作，将1~12月的英文单词压入栈中，然后将其取出
     *
     * 关联点学习：
     * 1）Stack和Vector内部数据结构和主要逻辑学习（Doing）
     *
     *
     * 问题点答疑：
     * 1）Vector扩容的主要逻辑是怎样的？是按两倍扩容吗？
     *    解答：创建Vector对象时，可以指定capacityIncrement增长的容量，若指定了则按 oldCapacity + capacityIncrement扩容
     *         否则按oldCapacity + oldCapacity扩容，即按两倍扩容。
     *
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
    public void test_by_stack() { //Done
        Stack stk = new Stack();
        for (int i = 0; i < months.length; i++) {
            stk.push(months[i] + ""); //入栈（在数组尾部添加元素）
        }
        System.out.println("stk = " + stk);
        stk.addElement("The last line"); //添加栈元素
        System.out.println("element 5 = " + stk.elementAt(5)); //取栈中指定位置的元素
        System.out.println("popping elements:");
        while (!stk.empty()) {
            System.out.println(stk.pop()); //出栈（获取数组尾部的元素）
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
         * 结果分析：
         * 1）Stack的push()、addElement()都能添加元素，Stack栈是后进先出
         *
         * 2）从Stack源码来看，Stack继承了Vector（向量），内部是使用数组来存储元素的
         *    在数组尾部插入的元素，即 elementData[elementCount++] = obj;
         *    在插入元素前，要判断是否超过数组长度，是否要进行扩容的
         *
         */
    }

    /**
     * 场景2：使用队列实现
     */
    @Test
    public void test_by_queue() { //Doing
        Vector vq = new Vector();
        for (int i = 0; i < months.length; i++) {
            vq.addElement(months[i] + "");
        }

        if (vq.isEmpty()) {
            System.out.println("kong");
        }

        while (!vq.isEmpty()) {
            System.out.println(vq.lastElement());
            vq.removeElement(vq.lastElement()); //获取队列元素后，移除最后一个元素
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
         * 1）使用队列来模拟栈功能，因为队列是先进先出，所以要实现后进先出，就需要从最后元素开始取值
         * 2）Vector中按数组进行存储，并提供在数组尾部添加元素和获取元素的方法，所以可以用于模拟栈的功能
         */
    }
}
