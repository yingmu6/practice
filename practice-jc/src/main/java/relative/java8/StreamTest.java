package relative.java8;

import lombok.Data;

import java.util.List;
import java.util.Optional;

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
    }
}

@Data
class Person {
    private String name;
    private int age;
}
