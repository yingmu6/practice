package relative.basic.test;

import java.util.Objects;

/**
 * 运算符优先级测试
 *
 * @author chensy
 * @date 2022/5/24
 */
public class OperatorTest {

    public static void main(String[] args) {
        Integer a = 1;
        System.out.println(Objects.nonNull(a) && !a.equals(0)); // !优先级高于 &&

        System.out.println(Objects.nonNull(a) && !false); // !优先级高于 &&


//        System.out.println(!StringUtils.isAnyEmpty("", "11"));
        

    }
}
