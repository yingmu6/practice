package relative.java8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : chensy
 * Date : 2020/11/17 下午9:03
 */
public class StreamTest {
    public static void main(String[] args) {
//        boolean isNeedNotice = true;
//        List<Person> dataPointVOS = Lists.newArrayList();
//        DataPointVO dataPointVO1 = new DataPointVO();
//        dataPointVO1.setCode("aa");
//        dataPointVO1.setValue("false");
//        DataPointVO dataPointVO2 = new DataPointVO();
////        dataPointVO2.setCode("bb");
//        Map<String, String> categoryAndCodeMap = new HashMap<>();
//        categoryAndCodeMap.put("ttt", "aa");
//
//        dataPointVOS.add(dataPointVO1);
//        dataPointVOS.add(dataPointVO2);
//        String disturbCategoryCode = "ttt";
////        dataPointVOS.stream().filter(a -> a.getValue().equals(disturbCategoryCode)).filter(Objects::nonNull).findFirst().get().getValue();
//        isNeedNotice = !TRUE.equals(dataPointVOS.stream().filter(e -> e.getCode().equals(categoryAndCodeMap.get(disturbCategoryCode))).findFirst().get().getValue());
//
//        Optional option = Optional.empty();
//        System.out.println(option.isPresent());
//        System.out.println(isNeedNotice);

//        orderPerson();
//        mapTest();
//        testEmptyList();

        testStreamMap();
    }

    public static void testStreamMap() {
        Person person1 = new Person();
        person1.setName("Zhang");
        person1.setAge(10);

        Person person2 = new Person();
        person2.setName("LiSi");
        person2.setAge(22);

        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);

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
//        Person person1 = new Person();
//        person1.setAge(1);
//        personList.add(person1);

        List<Person> newList = personList.stream().
                filter(x -> x.getAge() != 0).
                collect(Collectors.toList());
        System.out.println(JSON.toJSONString(newList));
    }

    // 测试stream的map功能
    public static void mapTest() {
        Person person1 = new Person();
        person1.setName("Zhang");
        person1.setAge(44);

        Person person2 = new Person();
        person2.setName("LiSi");
        person2.setAge(66);

        List<Person> personList = Lists.newArrayList();
        personList.add(person1);
        personList.add(person2);

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

        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setGmtCreate(189L);

        Person person2 = new Person();
        person2.setName("李李");
        person2.setAge(13);
        person2.setGmtCreate(188L);

        Person person3 = new Person();
        person3.setName("王王");
        person3.setAge(19);
        person3.setGmtCreate(187L);

        List<Person> personList = Lists.newArrayList();
        personList.add(person);
        personList.add(person2);
        personList.add(person3);

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
}

@Data
class Person {
    private String name;
    private int age;
    private Long gmtCreate;
}

@Data
class Car {
    private String carName;
}
