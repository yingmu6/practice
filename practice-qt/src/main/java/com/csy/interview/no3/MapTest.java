package com.csy.interview.no3;

import com.csy.interview.no3.collect_ext.HS;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author chensy
 * @date 2023/8/22
 */
public class MapTest {

    /**
     * Map_测试
     */

    /**
     * 场景1：hash冲突
     */
    @Test
    public void test_hash_conflict() {
        HashMap<HS, String> map = new HashMap<HS, String>();

        // 存入hashCode相同的HS对象
        map.put(new HS(), "1");
        map.put(new HS(), "2");
        System.out.println(map);

        // 存入hashCode、equals相同的HS对象
        map.put(new HS(){
           @Override
           public boolean equals(Object obj) {
               return true;
           }
        }, "3");

        System.out.println(map);

        // 存入hashCode不同，equals相同的HS对象
        map.put(new HS() {
            @Override
            public int hashCode() {
                return 2;
            }

            @Override
            public boolean equals(Object obj) {
                return true;
            }
        }, "3");
        System.out.println(map);

        /**
         * 输出结果：
         * {com.csy.interview.no3.collect_ext.HS@1=1, com.csy.interview.no3.collect_ext.HS@1=2}
         * {com.csy.interview.no3.collect_ext.HS@1=3, com.csy.interview.no3.collect_ext.HS@1=2}
         * {com.csy.interview.no3.collect_ext.HS@1=3, com.csy.interview.no3.collect_ext.HS@1=2, com.csy.interview.no3.MapTest$2@2=3}
         *
         * 结果分析：
         * 1）虽然HS对象的hashCode相同，但存入内容不一样，所以没有冲突，正常存入
         * 2）因为hashCode、equals都相等，所以发生了冲突，存入的第三个对象就替换第一个对象，所以HashMap中总共两个对象
         * 3）hashCode与HashMap已存入的HS对象不同，所以没有发生冲突，可以正常存入对象，存入后共三个对象
         *
         * 结果总结：
         * 当且仅当hashCode一直，且equals比对已知的对象，才被HashMap认为是同一个对象
         */
    }
}
