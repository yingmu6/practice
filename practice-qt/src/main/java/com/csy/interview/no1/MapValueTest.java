package com.csy.interview.no1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/7/3
 */
public class MapValueTest {

    /**
     * 场景1：Map的值测试
     */
    @Test
    public void test_map_value() {
        Map<String, String> map = new HashMap<>();
        map.put(String.valueOf(System.currentTimeMillis()) + "a", "1");
        map.put(String.valueOf(System.currentTimeMillis()) + "a", "2");
        map.put(String.valueOf(System.currentTimeMillis()) + "a", "3");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        /**
         * 输出结果：
         * 输出的结果结果，不确定的。
         * 可能只输出3，也可能输出1，2，3
         *
         * 结果分析：
         * 结果不确定，主要是System.currentTimeMillis()值，这个是对应毫秒值。因为现在的计算机运行速度比较快
         * 所以这个值可能会相等，而Map中相同key时，就会出现值覆盖。所以直接运行时会输出3，debug有停顿时，会输出1、2、3
         */
    }
}
