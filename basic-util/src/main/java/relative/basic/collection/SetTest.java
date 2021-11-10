package relative.basic.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.dubbo.common.utils.ConcurrentHashSet;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chensy
 * @date 2021/2/13
 */
public class SetTest {
    private static final Map<String, Set<Fruit>> LOG_ENTRIES = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Fruit fruit = new Fruit(12.0, 13.0);
        log("aa", fruit);
        Fruit fruit2 = new Fruit(13.0, 14.0);
        log("bb", fruit2);

    }

    private static void log(String accessLog, Fruit fruit) {
        Set<Fruit> logSet = LOG_ENTRIES.computeIfAbsent(accessLog, k -> new ConcurrentHashSet<>());
        logSet.add(fruit); //此处集合logSet改变，也会引起LOG_ENTRIES这个map的改变
        System.out.println(logSet.size());

    }
}

@Setter
@Getter
@Builder
@AllArgsConstructor
class Fruit {
    private Double weight;
    private Double price;
}
