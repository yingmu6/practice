package basic.tool.lombok;

import com.alibaba.fastjson.JSON;
import lombok.*;

/**
 * @author chensy
 * @date 2022/5/11
 */
public class LombokTest {

    public static void main(String[] args) {
        TestA testA = TestA.builder()
                .name("张三")
                .build();
        System.out.println(testA);
        System.out.println(testA.toString());

//        String str = testA.toString();

        String str = JSON.toJSONString(testA);
        TestA testA1 = JSON.parseObject(str, TestA.class);
        System.out.println(testA1.getName());

//        TestA testA2 = new TestA(); 解析：若构造函数设置了AccessLevel.PRIVATE，
//        会在构造函数加上private修改，不可访问，若完全使用@Builder方式，可以把构造函数都设置为私有的，还可以把@Setter方法设置为私有的
    }
}

@Getter
@Setter
@Builder
@ToString //输出的字符串不是json字符串
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
class TestA {
    String name;
}
