package com.csy.interview.written_exam.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/8/22
 */
public class MapTest { //@MsY-Doing

    /**
     * Map_测试
     * 1）负载因子、阈值的计划公式
     *    a）threshold = capacity * loadFactor
     *    b）loadFactor = size / capacity
     */

    /**
     * 场景1：hash冲突
     */
    @Test
    public void test_hash_conflict() { //Doing
        HashMap<HS, String> map = new HashMap<HS, String>();

        // 存入hashCode相同的HS对象
        map.put(new HS(), "1"); //在put时，会计算节点的hash值，即会调用HS重写的hashCode方法
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
         * {com.csy.interview.written_exam.collection.HS@1=1, com.csy.interview.written_exam.collection.HS@1=2}
         * {com.csy.interview.written_exam.collection.HS@1=3, com.csy.interview.written_exam.collection.HS@1=2}
         * {com.csy.interview.written_exam.collection.HS@1=3, com.csy.interview.written_exam.collection.HS@1=2, com.csy.interview.written_exam.collection.MapTest$2@2=3}
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

    /**
     * 场景2：
     */
    @Test
    public void basicUse2() {
        System.out.println("Use user defined class as key");
        HashMap<String, String> hm = new HashMap<>();
        hm.put("aaa", "bbb");
        hm.put("aaa", "ccc");

        Iterator iter = hm.entrySet().iterator();;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            System.out.println(key + " " + val);
        }
    }

    /**
     * 场景3：
     */
    @Test
    public void basicUse3() {
        System.out.println("Use String as key");
        HashMap<PersonInfo, String> hm = new HashMap<>();
        PersonInfo p1 = new PersonInfo("111", "name1");
        PersonInfo p2 = new PersonInfo("111", "name1");
        hm.put(p1, "address1");
        hm.put(p2, "address2");
        Iterator iter = hm.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            PersonInfo key = (PersonInfo) entry.getKey();
            String val = (String) entry.getValue();
            System.out.println("key=" + key + "  value=" + val);
        }
    }

    /**
     * 场景4：
     */
    @Test
    public void basicUse4() {
        System.out.println("Use String as key");
        HashMap<PersonInfo2, String> hm = new HashMap<>();
        PersonInfo2 p1 = new PersonInfo2("111", "name1");
        PersonInfo2 p2 = new PersonInfo2("111", "name2");
        hm.put(p1, "address1");
        hm.put(p2, "address2");

        Iterator iter = hm.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            PersonInfo2 key = (PersonInfo2) entry.getKey();
            String val = (String) entry.getValue();
            System.out.println("key=" + "key" + " value=" + val);
        }
    }

    class PersonInfo {

        String id;

        String name;

        public PersonInfo(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return "id = " + id + ",name = " + name;
        }
    }

    class PersonInfo2 {

        String id;

        String name;

        public int hashCode() {
            return id.hashCode();
        }

        public PersonInfo2(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return "id = " + id + ",name = " + name;
        }

        public boolean equals(Object obj) {
            PersonInfo2 p = (PersonInfo2) obj;
            if (p.id.equals(this.id)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
