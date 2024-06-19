package relative.basic.map_test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.List;

/**
 * @author chensy
 * @date 2022/5/9
 */
public class EnumMapTest {
    public static void main(String[] args) {

        EnumMap enumMap = getEnumMap();
        List<Student> aList = (List<Student>) enumMap.get(Fruit.APPLE);
        List<Student> bList = (List<Student>) enumMap.get(Fruit.ORANGE);
        System.out.println("a列表" + JSON.toJSONString(aList));
        System.out.println("b列表" + JSON.toJSONString(bList));
    }

    private static EnumMap getEnumMap() {
        EnumMap enumMap = new EnumMap(Fruit.class);
        List<Student> studentList = Lists.newArrayList();
        Student a1 = new Student();
        a1.setName("zhang");
        Student a2 = new Student();
        a2.setName("li");
        studentList.add(a1);
        studentList.add(a2);

        List<Student> studentList2 = Lists.newArrayList();
        Student a3 = new Student();
        a3.setName("wang");
        studentList2.add(a3);

        enumMap.put(Fruit.APPLE, studentList);
        enumMap.put(Fruit.ORANGE, studentList2);

        return enumMap;
    }
}

@Getter
@Setter
class Student {
    private String name;
}

@Getter
enum Fruit {
    APPLE, ORANGE;
}
