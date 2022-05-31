package relative.basic.collection;

import com.google.common.collect.Sets;
import lombok.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chensy
 * @date 2021/2/13
 */
public class SetTest {
    private static final Map<String, Set<Fruit>> LOG_ENTRIES = new ConcurrentHashMap<>();

    public static void main(String[] args) {
//        toList();
        basic();
    }

    public static void basic() {
        Set<String> set = Sets.newHashSet();
        System.out.println(set.add("111"));
        System.out.println(set.add("222"));
        System.out.println(set.add("222"));
        System.out.println(set);
    }

    // 转换为List的方式
    public static void toList() {
        Set<Fruit> set = getSet();
        // set转list
        List<Fruit> list = new ArrayList<>(set);

        // list转set
        Set<Fruit> set2 = new HashSet<>(list);
        System.out.println(list + "," + list.getClass());
        System.out.println(set2 + "," + set2.getClass());
    }

    public static Set<Fruit> getSet() {
        Set<Fruit> set = new HashSet<>();
        Fruit fruit = new Fruit(12.0, 13.0);
        Fruit fruit2 = new Fruit(13.0, 14.0);
        set.add(fruit);
        set.add(fruit2);
        return set;
    }

    private static void log(String accessLog, Fruit fruit) {
//        Set<Fruit> logSet = LOG_ENTRIES.computeIfAbsent(accessLog, k -> new ConcurrentHashSet<>());
//        logSet.add(fruit); //此处集合logSet改变，也会引起LOG_ENTRIES这个map的改变
//        System.out.println(logSet.size());

    }
}

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
class Fruit {
    private Double weight;
    private Double price;
}
