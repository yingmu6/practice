package com.csy.structure.linked;

import com.csy.structure.linked.two_way.TwoWayList;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/9/5
 */
public class TwoWayListTest {

    /**
     * 双线列表_测试 todo @csy 待调试
     */
    @Test
    public void test_two_way_link() {
        TwoWayList<Integer> list = new TwoWayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(list.get(1));

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）为什么报Invalid Index？
         */
    }
}
