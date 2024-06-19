package relative.java8.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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
     *     4）https://reflectoring.io/comprehensive-guide-to-java-streams 比较详细的英文介绍
     *     5）https://www.cnblogs.com/54chensongxia/p/14388029.html idea调试技巧（包含Stream调试）
     */

    /**
     * 调试Stream
     */
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = nums.stream().filter(i -> i >= 2).map(i -> ++i).collect(Collectors.toList());
        System.out.println(result);

        /**
         * debug调试技巧：
         * 1）将断点设置到lambda表达式出，然后点击debug调试面板上的"trace current stream chain"
         *    就可以看到每个stream操作后的数据，比如filter操作后的数据、map后的数据、collect后的数据
         *    （以前没用过，还真是好用，写stream表达式就容易验证了）
         */
    }

    /**
     * 场景1：顺序流
     */
    @Test
    public void testSequential() { //顺序流：都是有同一个线程运行，比如使用main线程
        // 顺序Stream
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        listOfNumbers.stream().forEach(number ->
                System.out.println("串行Stream：" + number + " " + Thread.currentThread().getName())
        );
        /**
         * 输出结果：
         * 串行Stream：1 main
         * 串行Stream：2 main
         * 串行Stream：3 main
         * 串行Stream：4 main
         *
         * 结果分析：
         * 未指定并行流，默认是窜行流，就会在一个线程中，依次执行
         */
    }

    /**
     * 场景2：并行流
     * 1）Java8 Stream 流在执行时候是串行化的, 如果说任务执行的耗时比较长, 可以使用Stream的"并行流" ParallelStream
     * 2）并非耗时就一定要使用并行, 根据不同的业务场景, 合理的使用即可
     * 3）parallelStream 是一种并行流, 意思为处理任务时并行处理, 这里和并发编程就有了千丝万缕的关系
     * （前提是硬件支持, 如果单核CPU, 只会存在并发处理, 而不会并行）
     * 4）并发处理就需要考虑线程安全问题
     * 5）并行流底层就是使用的 ForkJoinPool, 一种 工作窃取算法线程池
     *    ForkJoinPool 的优势在于, 可以充分利用多 CPU 的优势，把一个任务拆分成多个"小任务",
     *    把多个"小任务"放到多个处理器核心上并行执行; 当多个"小任务"执行完成之后, 再将这些执行结果合并起来
     * 6）It's also very easy to create streams that execute in parallel and make use of multiple processor cores.
     *  （并行流是容易创建使用多处理器的流）
     */
    @Test
    public void testParallelStream() { //测试并行流：由多个线程并发执行
        List<Integer> listOfNumbers2 = Arrays.asList(1, 2, 3, 4);
        listOfNumbers2.parallelStream().forEach(number ->
                System.out.println("并行Stream：" + number + " " + Thread.currentThread().getName())
        );

        /**
         * 结果输出：
         * 并行Stream：3 main
         * 并行Stream：4 ForkJoinPool.commonPool-worker-2
         * 并行Stream：1 main
         * 并行Stream：2 ForkJoinPool.commonPool-worker-1
         *
         * 结果分析：
         * 运行结果不确定，使用多个线程执行，会随机执行
         */
    }

    /**
     * 场景3：将流中的内容分摊开，如将二维数组分摊为一维数组
     * 1）使用flatMap，flat：平的，平躺的
     */
    @Test
    public void test_flatMap() {
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

    /**
     * 场景4：匹配任意元素
     * 1）anyMatch：期待Stream中的所有元素，都与指定谓词Predicate匹配，与noneMatch相反
     */
    @Test
    public void testAnyMatch() {
        List<Person> personList = getPersonList();
        boolean isExist = personList.stream().anyMatch(x -> x.getAge() == 17);
        System.out.println("是否存在满足条件的任意元素，isExist=" + isExist); //输出：false

        boolean isExist2 = personList.stream().anyMatch(x -> x.getAge() == 18);
        System.out.println("是否存在满足条件的任意元素，isExist2=" + isExist2); //输出：true
    }

    /**
     * 场景5：测试过滤功能
     * 1）过滤出满足条件的元素
     */
    @Test
    public void testFilter() {
        List<Person> personList = getPersonList();
        // filter()过滤元素，保留符合条件的元素
        List<Person> newList = personList.stream()
                .filter(x -> x.getAge() == 19)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(newList)); //输出：将Person属性age==19的元素保留下来
    }

    /**
     * 场景6：group by 分组功能
     */
    @Test
    public void testGroupBy() {
        List<Person> personList = getPersonList();
        // 映射时，若映射的key为空，会报"element cannot be mapped to a null key"
        Map<Integer, List<Person>> mapList = personList.stream().collect(Collectors.groupingBy(x -> x.getOriginType())); //将流中的元素看originType进行分组

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
        
        System.out.println("第一个Map：" + mapList); //分组
        System.out.println("第二个Map：" + mapList2);
        System.out.println("第三个Map：" + mapList3);
        System.out.println("第四个Map：" + mapList4);
    }

    // 多个group by 条件
    private static String multiCondition(Person person) {
        return person.getName() + person.getOriginType();

    }

    /**
     * 场景7：测试将流中的元素转换为Map形式
     */
    @Test
    public void test_streamMap() {
        List<Person> personList = getPersonList();
        // 函数式接口是可以用lambda表达式写的（先找到函数式接口，除去default方法，看是否有入参、出参，然后在根据lambda表达式写）
        // 如toMap()方法需要两个参数都是函数式接口Function，而这个接口的方法是R apply(T t); 参数都是泛型，类型都可以定义，这里说明是接收一个参数，然后返回一个参数
        Map<String, Integer> map = personList.stream().collect(Collectors.toMap(x -> x.getName(), y -> y.getAge()));
        map.entrySet().stream().forEach(x -> {
            System.out.println("键：" + x.getKey() + "，值：" + x.getValue());
        });

        /**
         * 结果输出：
         * 键：张三，值：18
         * 键：李李，值：19
         * 键：王王，值：19
         */
    }

    /**
     * 场景8：测试空的列表，进行Steam操作
     * 1）空列表的Stream操作，不会出现NPE
     */
    @Test
    public void test_emptyList() {
        List<Person> personList = Lists.newArrayList();
        List<Person> newList = personList.stream().
                filter(x -> x.getAge() != 0).
                collect(Collectors.toList());
        System.out.println(JSON.toJSONString(newList)); // 输出：[]
    }

    /**
     * 场景9：stream的map功能
     */
    @Test
    public void test_map() {
        List<Person> personList = getPersonList();
        List<Car> carList = personList.stream()
                .map(x -> {
                    Car car = new Car();
                    car.setCarName(x.getName());
                    return car;
                }).collect(Collectors.toList());
        System.out.println("Map转化结果:" + JSON.toJSONString(carList));

        /**
         * 输出结果：
         * Map转化结果:[{"carName":"张三"},{"carName":"李李"},{"carName":"王王"}]
         */
    }

    /**
     * 场景10：排序
     */
    @Test
    public void test_order() {
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

    /**
     * 场景11：Stream创建
     */
    @Test
    public void test_create_stream() {
        // 创建方式一：使用Arrays.stream创建
        double[] elements = {3.0, 4.5, 6.7, 2.3};
        DoubleStream stream = Arrays.stream(elements);
        stream.forEach(System.out::println);

        // 创建方式二：使用集合创建
        Double[] elements2 = {4.6, 5.8};
        List<Double> elementsInCollection = Arrays.asList(elements2);
        Stream<Double> stream2 = elementsInCollection.stream();
        stream2.forEach(System.out::println);
    }

    /**
     * 场景12：reduce使用
     * reduce()方法将一个Stream的每个元素依次作用于BinaryOperator，并将结果合并。
     * reduce()是聚合方法，聚合方法会立刻对Stream进行计算
     */
    @Test
    public void test_reduce() {
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(0, (acc, n) -> acc + n);
        System.out.println(sum);
    }

    /**
     * 场景N：Stream#noneMatch(java.util.function.Predicate) 方法使用
     */
    @Test
    public void test_noneMatch() {
        /**
         * 分析：
         * 1）none：全无，noneMatch：全部不匹配
         * 2）Returns whether no elements of this stream match the provided predicate.
         * （判断此流中是否没有元素与所提供的谓词匹配）
         * 3）This is a short-circuiting terminal operation （这是一个短路操作）
         *
         * 总结：
         * noneMatch可理解为：期望Stream流中的元素与指定的Predicate谓词全不匹配，若都是则返回true，否则返回false
         */
        Integer[] arr = new Integer[] {1, 3, 5};
        System.out.println("第一个匹配：" + Arrays.stream(arr).noneMatch(var -> var.equals(3))); //输出：false
        System.out.println("第二个匹配：" + Arrays.stream(arr).noneMatch(var -> var.equals(6))); //输出：true
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
