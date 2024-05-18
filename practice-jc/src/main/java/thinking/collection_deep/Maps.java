package thinking.collection_deep;

import net.mindview.util.CountingMapData;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class Maps { //@TkY-Doing

    /**
     * 知识点：Map
     *
     * 知识点概要：
     * 1）
     */

    public static void printKeys(Map<Integer, String> map) {
        printnb("Size = " + map.size() + "，");
        printnb("Keys：");
        print(map.keySet());
    }

    public static void test(Map<Integer, String> map) {
        print(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        map.putAll(new CountingMapData(25));
        printKeys(map);
        printnb("Values：");
        print(map.values());
        print(map);
        print("map.containsKey(11)：" + map.containsKey(11));
        print("map.get(11)：" + map.get(11));
        print("map.containsValue(\"F0\")："
        + map.containsKey("F0"));
        Integer key = map.keySet().iterator().next();
        print("First key in map：" + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        print("map.isEmpty()：" + map.isEmpty());
        map.putAll(new CountingMapData(25));
        map.keySet().removeAll(map.keySet());
        print("map.isEmpty()：" + map.isEmpty());
    }

    /**
     * 场景1：HashMap测试
     */
    @Test
    public void testHashMap() { //Doing

        test(new HashMap<>());

        /**
         * 输出结果：
         * HashMap
         * Size = 25，Keys：[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
         * Values：[A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, R0, S0, T0, U0, V0, W0, X0, Y0]
         * {0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0, 10=K0, 11=L0, 12=M0, 13=N0, 14=O0, 15=P0, 16=Q0, 17=R0, 18=S0, 19=T0, 20=U0, 21=V0, 22=W0, 23=X0, 24=Y0}
         * map.containsKey(11)：true
         * map.get(11)：L0
         * map.containsValue("F0")：false
         * First key in map：0
         * Size = 24，Keys：[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
         * map.isEmpty()：true
         * map.isEmpty()：true
         *
         * 结果分析：
         * 1）
         *
         */
    }

    /**
     * 场景2：TreeMap测试
     */
    @Test
    public void testTreeMap() {

        test(new TreeMap<>());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         */
    }

    /**
     * 场景3：LinkedHashMap测试
     */
    @Test
    public void testLinkedHashMap() {

        test(new LinkedHashMap<>());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         */
    }

    /**
     * 场景4：IdentityHashMap测试
     */
    @Test
    public void testIdentityHashMap() {

        test(new IdentityHashMap<>());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         */
    }

    /**
     * 场景5：ConcurrentHashMap测试
     */
    @Test
    public void testConcurrentHashMap() {

        test(new ConcurrentHashMap<>());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         */
    }

    /**
     * 场景6：WeakHashMap测试
     */
    @Test
    public void testWeakHashMap() {

        test(new WeakHashMap<>());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         */
    }
}
