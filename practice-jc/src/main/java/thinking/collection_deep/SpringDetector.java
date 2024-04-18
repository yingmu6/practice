package thinking.collection_deep;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class SpringDetector {

    /**
     * 知识点：散列和散列码（散列即HashMap）
     * 1）HashMap是由Node<K,V>[]节点数组、以及容量、负载因子组成的。每个Node<K,V>主要包含hash、K、V
     *
     * 2）HashMap#containsKey(key)是否包含指定key的逻辑（本质：hash值、Key、Value三者的值都相等）
     *    a）会先计算key的hash值，然后与tab.length-1做按位与&运算，得到hash值对应的数组下标，从而找到用于比较的节点first
     *    b）将first节点与将传入的key对应的hash值做比较，并且用==比较两个引用的地址，用equal比较两个对象内容，若都相等，则找到指定节点。
     *    c）未通过b判断时，判断first.next是否有下一个节点，有表明关联着链表
     *       c.1）判断节点是否是TreeNod节点，若是则通过树查找方法去查找节点
     *       c.2）若不是树结构，表明是链表接口，就循环判断链表中所有节点，看是否能找到匹配的元素
     *
     * 3）HashMap中求hash值的方法时final修饰的，所以不能重写，逻辑为：return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16) ；
     *
     * todo @Ym HashMap中的put逻辑待实践
     */

    static class Groundhog {
        protected int number;
        public Groundhog(int number) {
            this.number = number;
        }
        public String toString() {
            return "Groundhog #" + number;
        }
    }

    static class Prediction {
        private static Random rand = new Random(47);
        private boolean shadow = rand.nextDouble() > 0.5;
        public String toString() {
            if (shadow) {
                return "Six more weeks of Winter!";
            } else {
                return "Early Spring!";
            }
        }
    }

    public <T extends Groundhog> void detectSpring(Class<T> type) throws Exception {
        Constructor<T> ghog = type.getConstructor(int.class);
        Map<Groundhog, Prediction> map = new HashMap<>();
        for (int i = 0; i < 10; i++) { //以1到9作为参数，构造Groundhog实例，并作为key存入map
            map.put(ghog.newInstance(i), new Prediction());
        }
        System.out.println("map = " + map);
        Groundhog gh = ghog.newInstance(3); //gh和map中用3作为构造参数的对象，两着之间不是同一个对象
        System.out.println("Looking up predication for " + gh);

        if (map.containsKey(gh)) {
            System.out.println(map.get(gh));
        } else {
            System.out.println("Key not found: " + gh);
        }
    }

    /**
     * 场景1：HashMap的key为对象类型
     */
    @Test
    public void keyOfObject() throws Exception {
        detectSpring(Groundhog.class);

        /**
         * 输出结果：
         * map = {Groundhog #5=Early Spring!, Groundhog #1=Six more weeks of Winter!, Groundhog #0=Six more weeks of Winter!,
         * Groundhog #7=Early Spring!, Groundhog #4=Six more weeks of Winter!, Groundhog #2=Early Spring!,
         * Groundhog #3=Early Spring!, Groundhog #9=Six more weeks of Winter!, Groundhog #6=Early Spring!,
         * Groundhog #8=Six more weeks of Winter!}
         * Looking up predication for Groundhog #3
         * Key not found: Groundhog #3
         *
         * 结果分析：
         * 1）在map.put元素时，将参数3构建Groundhog对象，而gh也是将3参数构建Groundhog。两个对象虽然成员变量的值相等，但是对象的hashCode值不一样
         *    所以在containsKey(key)查找Map中是否包含指定元素时，会将hashCode、Key、Value都进行比较，3者都相等才相等，而hashCode不相等，所以找不到元素
         *
         */
    }

    /**
     * 场景2：HashMap的key为String类型
     */
    @Test
    public void keyOfString() {
        HashMap<String, String> map = new HashMap<>();
        String str1 = "name";
        map.put(str1, "zhang");
        map.put("address", "zhejiang");

        String str2 = "name";
        System.out.println("str1 hashCode = " + hashFromHashMap(str1) + "，str2 hashCode = " + hashFromHashMap(str2));
        System.out.println(map.containsKey(str2));

        String str3 = new String("name");
        System.out.println("str3 hashCode = " + hashFromHashMap(str3));
        System.out.println(map.containsKey(str3));

        StringBuffer buffer = new StringBuffer().append("name");
        System.out.println("buffer hashCode = " + hashFromHashMap(buffer));
        System.out.println(map.containsKey(buffer));

        /**
         * 输出结果：
         * str1 hashCode = 3373752，str2 hashCode = 3373752
         * true
         * str3 hashCode = 3373752
         * true
         * buffer hashCode = 861838996
         * false
         *
         * 结果分析：
         * 1) str1、str2、str3都是同一个对象，hash值一样，所以能找到指定元素
         * 2）StringBuffer虽然内容是同样的字符串值，但它不是同一个对象，hashCode不一样，所以就找不到元素
         *   （即存入和获取两个key对应的hash值不一样）
         *
         */
    }

    final int hashFromHashMap(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
