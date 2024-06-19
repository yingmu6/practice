package thinking.holder_obj;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/5
 */
public class PrintingContainers {

    /**
     * 知识点（11.4）：容器的打印
     */

    static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }

    static Map fill(Map<String, String> map) {
        map.put("rat", "Fuzzy");
        map.put("cat", "Rags");
        map.put("dog", "Bosco");
        map.put("dog", "Spot");
        return map;
    }

    public static void main(String[] args) {
        print(fill(new ArrayList<>()));
        print(fill(new LinkedList<>()));
        print(fill(new HashSet<>()));
        print(fill(new TreeSet<>()));
        print(fill(new LinkedHashSet<>()));
        print(fill(new HashMap<>()));
        print(fill(new TreeMap<>()));
        print(fill(new LinkedHashMap<>()));
    }
}
