package relative.basic.map_test.concurrentmap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chensy
 * @date 2021/8/30
 */
public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("ee", "333");
        map.putIfAbsent("ee", "444"); //在Map中没有指定key时，才对应设置value
        map.put("ee", "555");         //若存在key时，直接覆盖value值

        System.out.println(map); //输出：{ee=555}
        System.out.println(map.putIfAbsent("dd", "555")); //输出：null
        System.out.println(map.putIfAbsent("ee", "999")); //输出：555
    }
}
