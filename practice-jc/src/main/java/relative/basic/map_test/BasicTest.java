package relative.basic.map_test;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author chensy
 * @date 2022/5/10
 */
public class BasicTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("407", "hh");

        Map<String, String> map2 = new HashMap<>();
        map2.put("aaddcd", "hh");

        Map<Integer, Map<String, String>> mapMap = Maps.newHashMap();
        mapMap.put(1, map);
        mapMap.put(2, map2);

        System.out.println(mapMap);
        System.out.println(Optional.ofNullable(Optional.ofNullable(mapMap.get(3)).orElse(Maps.newHashMap()).get("407")).orElse("33"));
        System.out.println(Objects.isNull(mapMap.get(3)) ? "556" : mapMap.get(3).get("407")); //Map中的get() 若没有值，会返回Null，所以要防止空指针异常

//        System.out.println(Optional)

        // todo @csy  java.util.Map.remove(java.lang.Object)  移除后，返回值是怎样的？

    }
}

@Getter
@Setter
class A {
    private String name;
}
