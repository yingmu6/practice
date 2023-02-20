package relative.java8.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
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
    /**
     * 功能描述：
     *     1）Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
     *        Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
     *        Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
     *
     *     2）这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
     *       元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
     *
     *     3）Stream（流）是一个来自数据源的元素队列并支持聚合操作
     *       元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
     *       数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
     *       聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
     *
     *     和以前的Collection操作不同， Stream操作还有两个基础的特征：
     *       Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
     *       内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
     *
     *     4）在 Java 8 中, 集合接口有两个方法来生成流：
     *        stream() − 为集合创建串行流。
     *        parallelStream() − 为集合创建并行流。
     *
     *     5）A stream does not store data and, in that sense, is not a data structure. It also never modifies the underlying data source.
     *        流不存储数据，从某种意义上来说，它不是一个数据接口，它从来不修改数据源的数据
     *
     *     6）Stream支持函数式风格操作，也就是支持lambda表达式、
     *
     * 参考链接：
     *     1）https://www.runoob.com/java/java8-streams.html  菜鸟教程_java Stream流的介绍
     *     2）https://stackify.com/streams-guide-java-8/ 英文版介绍
     *     3）https://www.baeldung.com/java-when-to-use-parallel-stream 并行流英文介绍
     */

    public static void main(String[] args) {

//        orderPerson();
//        mapTest();
//        testEmptyList();
//        testStreamMap();

        // @csy anyMatch()待使用_2023-02-07 已执行
//        testGroupBy();
//        testFilter();

//        testAnyMatch();

//          testFlatMap();

        testSequential();
        testParallelStream();

        //todo @csy-23/02/20 nomatch方法使用
    }

    public static void testSequential() { //顺序流：都是有同一个线程运行，比如使用main线程
        // 顺序Stream
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        listOfNumbers.stream().forEach(number ->
                System.out.println("串行Stream：" + number + " " + Thread.currentThread().getName())
        );
    }
    public static void testParallelStream() { //测试并行流：由多个线程并发执行（todo @pause 待进一步探索）
        List<Integer> listOfNumbers2 = Arrays.asList(1, 2, 3, 4);
        listOfNumbers2.parallelStream().forEach(number ->
                System.out.println("并行Stream：" + number + " " + Thread.currentThread().getName())
        );

    }

    // 测试将元素展开
    public static void testFlatMap() {
        List<List<String>> nameList = Arrays.asList( //二元数组
                Arrays.asList("zhang","chen"),
                Arrays.asList("wang", "li", "liu")
        );

        // flatMap方法的参数为：Function<? super T, ? extends Stream<? extends R>> mapper
        // 即入参为泛型、返回值的类型为Stream类型，所以要使用产生Stream的表达式
        List<String> nameFlatList = nameList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()); //第一种写法

        List<String> nameFlatList2 = nameList.stream()
                .flatMap(x -> x.stream()) //第二种写法
                .collect(Collectors.toList());

        // 将二维数组的所有元素展开，形成一维数组
        System.out.println(JSON.toJSONString(nameFlatList));

        System.out.println(JSON.toJSONString(nameFlatList2));
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
