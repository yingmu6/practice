package relative.java8;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
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


        orderPerson();
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
