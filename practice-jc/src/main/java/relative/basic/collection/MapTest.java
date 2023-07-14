package relative.basic.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2022/5/19
 */
public class MapTest {
    public static void main(String[] args) {
//        private final ConcurrentMap<URL, Set<NotifyListener>> subscribed = new ConcurrentHashMap<>();
//        Set<NotifyListener> listeners = subscribed.computeIfAbsent(url, n -> new ConcurrentHashSet<>());
//        listeners.add(listener); 此处添加元素后，是否会Map有影响

        Map<String, String> map = new HashMap<>();
        map.put("name", "zhang");
        map.put("age", "11");
        System.out.println(map);

        Map<String, String> map1 = new HashMap<>();
        map1.put("version", "1.0.0");
        map1.put("application", "test");
        System.out.println(map1);

        /**
         * 输出结果：
         * {name=zhang, age=11}
         * {application=test, version=1.0.0}
         *
         * 结果分析：
         * HashMap并不会对key进行排序，而是根据计算的Hash值来插入元素的
         *
         * 参考链接：https://zhuanlan.zhihu.com/p/494172384
         */
    }
}
