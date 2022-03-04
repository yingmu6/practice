package basic.unit.mock;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2022/3/4
 */
public class Animal {
    Map<String, String> nameMap;

    public Animal() {
        nameMap = new HashMap<>();
    }

    public void add(String type, String name) {
        nameMap.put(type, name);
    }

    public String getAnimalName(String type) {
        return this.nameMap.get(type);
    }
}
