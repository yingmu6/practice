package relative.java8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : chensy
 * Date : 2020/11/17 下午9:03
 */
public class StreamTest {
    public static void main(String[] args) {

//        orderPerson();
//        mapTest();
//        testEmptyList();
//        testStreamMap();

        // @csy anyMatch()待使用_2023-02-07 已执行
//        testGroupBy();
//        testFilter();

        testAnyMatch();
    }

    public static void testAnyMatch() {
        List<Person> personList = getPersonList();
        boolean isExist = personList.stream().anyMatch(x -> x.getAge() == 17);
        System.out.println("是否存在满足条件的任意元素，isExist=" + isExist);
    }

    public static void testFilter() {
        List<Person> personList = getPersonList();
        // filter()过滤元素，保留符合条件的元素
        List<Person> newList = personList.stream().filter(x -> x.getAge() == 19).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(newList));
    }

    // 测试stream的group by功能
    public static void testGroupBy() {
        List<Person> personList = getPersonList();
        // 映射时，若映射的key为空，会报"element cannot be mapped to a null key"
        Map<Integer, List<Person>> mapList = personList.stream().collect(Collectors.groupingBy(x -> x.getOriginType()));

        // x -> x.getOriginType() 写法与Person::getOriginType等价
        Map<Integer, List<Person>> mapList2 = personList.stream().collect(Collectors.groupingBy(Person::getOriginType));

        // 会将多个属性的值拼接，去group值（从整理值中拼接分组）
        // 例如映射结果： {张三1=[Person(name=张三, age=18, gmtCreate=189, originType=1)],
        // 王王2=[Person(name=王王, age=19, gmtCreate=188, originType=2), Person(name=王王, age=19, gmtCreate=187, originType=2)]}
        Map<Object, List<Person>> mapList3 = personList.stream().collect(Collectors.groupingBy(x -> multiCondition(x)));
        System.out.println("具体值：" + mapList3.get("王王2"));

        // 对映射后的Map，再次做处理（可以按照预期的结构，再次做映射处理，多个操作合并在一起处理）
        Map<Integer, Set<String>> mapList4 = personList.stream().collect(Collectors.groupingBy(Person::getOriginType,
                Collectors.mapping(x -> x.getName(), Collectors.toSet())));
        
        System.out.println("第一个Map：" + mapList);
        System.out.println("第二个Map：" + mapList2);
        System.out.println("第三个Map：" + mapList3);
        System.out.println("第四个Map：" + mapList4);
    }

    // 多个group by 条件
    private static String multiCondition(Person person) {
        return person.getName() + person.getOriginType();

    }

    // 测试将流中的元素转换为Map形式
    public static void testStreamMap() {
        List<Person> personList = getPersonList();
        // 函数式接口是可以用lambda表达式写的（先找到函数式接口，除去default方法，看是否有入参、出参，然后在根据lambda表达式写）
        // 如toMap()方法需要两个参数都是函数式接口Function，而这个接口的方法是R apply(T t); 参数都是泛型，类型都可以定义，这里说明是接收一个参数，然后返回一个参数
        Map<String, Integer> map = personList.stream().collect(Collectors.toMap(x -> x.getName(), y -> y.getAge()));
        map.entrySet().stream().forEach(x -> {
            System.out.println("键：" + x.getKey() + "，值：" + x.getValue());
        });
        System.out.println(map);
    }

    public static void testEmptyList() {
        List<Person> personList = Lists.newArrayList();
        List<Person> newList = personList.stream().
                filter(x -> x.getAge() != 0).
                collect(Collectors.toList());
        System.out.println(JSON.toJSONString(newList));
    }

    // 测试stream的map功能
    public static void mapTest() {
        List<Person> personList = getPersonList();
        List<Car> carList = personList.stream()
                .map(x -> {
                    Car car = new Car();
                    car.setCarName(x.getName());
                    return car;
                }).collect(Collectors.toList());
        System.out.println("Map转化结果:" + JSON.toJSONString(carList));
    }

    // 排序
    public static void orderPerson() {
        List<Person> personList = getPersonList();
        List<Person> newList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println("第一个：" + newList);

        List<Person> newList2 = personList.stream()
                .filter(x -> !StringUtils.isEmpty(x.getName()))
                .sorted(Comparator.comparing(Person::getName).reversed()) //降序
                .collect(Collectors.toList());
        System.out.println("第二个：" + newList2);

        List<Person> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getGmtCreate)) //自然排序
                .collect(Collectors.toList());
        System.out.println("第三个：" + newList3);
    }


    // 基础功能：获取用户列表
    public static List<Person> getPersonList() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setGmtCreate(189L);
        person.setOriginType(1);

        Person person2 = new Person();
        person2.setName("李李");
//        person2.setName("王王");
        person2.setAge(19);
        person2.setGmtCreate(188L);
        person2.setOriginType(2);

        Person person3 = new Person();
        person3.setName("王王");
        person3.setAge(19);
        person3.setGmtCreate(187L);
        person3.setOriginType(2);

        List<Person> personList = Lists.newArrayList();
        personList.add(person);
        personList.add(person2);
        personList.add(person3);

        return personList;
    }
}

@Data
class Person {
    private String name;
    private Integer age;
    private Long gmtCreate;
    private Integer originType;
}

@Data
class Car {
    private String carName;
}
