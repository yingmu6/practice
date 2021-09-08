package relative.java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 分页处理
 *
 * @author chensy
 * @date 2021/8/30
 */
public class PageTest {
    public static void main(String[] args) {
        List<String> ids = Stream.of("11", "22", "33", "44", "55").collect(Collectors.toList());
        int pageSize = 2;
        int page = (ids.size() / pageSize) + 1;
        for (int i = 1; i <= page; i++) {
            System.out.println(getFilterList(ids, i, pageSize));
        }
    }

    public static List<String> getFilterList(List<String> ids, int pageNo, int pageSize) {
        return ids.stream().skip((pageNo - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }
}
