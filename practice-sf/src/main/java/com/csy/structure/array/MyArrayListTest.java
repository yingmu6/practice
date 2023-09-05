package com.csy.structure.array;

import com.csy.structure.array.ext.MyArrayList;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/9/5
 */
public class MyArrayListTest {

    /**
     * 自定义ArrayList_测试
     *
     *
     * 1）ArrayList的底层原理
     */

    @Test
    public void test_my_array_list() { //todo @csy 待调试
        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.indexOf(4));
        System.out.println(list.contain(2));
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         *
         */
    }
}
