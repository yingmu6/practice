package relative.java8.stream;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chensy
 * @date 2022/4/14
 */
public class FilterTest {

    /**
     * filter方法的作用：
     * 1）Returns a stream consisting of the elements of this stream that match the given predicate
     * (原文翻译：返回由与给定谓词匹配的此流的元素组成的流)
     * 2）filter可以给定的指定lambda表达式或方法【接收一个参数，并能返回boolean值】
     * 3）可以使用groupingBy()分组，返回值为Map<Object, List<Object>
     */
    public static void main(String[] args) {
        List<String> aList = Lists.newArrayList("123", "11aa", "222cc", "");

        List<String> bList = aList.stream()
                .filter(x -> StringUtils.isEmpty(x)) //按lambda表达式过滤
                .collect(Collectors.toList());
        System.out.println(bList + "," + bList.size());

        FilterTest filterTest = new FilterTest();
        filterTest.filterByFunction(aList);

        filterTest.byGroup();
    }

    private void filterByFunction(List<String> bList) {
        List<String> cList = bList.stream()
                .filter(this::filterData) //按指定方法表达式过滤
                .collect(Collectors.toList());
        System.out.println("cList:" + cList + "," + cList.size());
    }

    private Boolean filterData(String originStr) {
        return StringUtils.isEmpty(originStr);
    }

    // 按指定条件分组
    private void byGroup() {
        List<String> aList = Lists.newArrayList("123", "11aa", "222cc", "333");
        Map<String, List<String>> map = aList.stream().collect(Collectors.groupingBy(x -> {
            if (x.startsWith("1")) {
                return "11***";
            } else if (x.startsWith("2")) {
                return "22***";
            } else {
                return "other";
            }
        }));

        System.out.println(map);
    }
}
