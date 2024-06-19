package relative.java8.optional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author chensy
 * @date 2023/6/21
 */
public class OptionalTest {

    /**
     * Optional_概述
     * 1）The purpose of the class is to provide a type-level solution for representing optional values instead of null references.
     *   （这个类的意图是为了解决null引用的问题）
     *
     * 参考：
     * 1）https://www.baeldung.com/java-optional Optional参考
     */

    /**
     * 场景1：Optional创建方式
     */
    @Test
    public void test_create_way() {

        // 方式一：创建空的Optional
        Optional<String> empty = Optional.empty();
        Assert.assertFalse(empty.isPresent());

        // 方式二：使用of创建（参数不能是null值，会抛出NPE）
        String name = "zhang";
        Optional<String> opt = Optional.of(name);
        Assert.assertTrue(opt.isPresent());

        // 方式三：使用ofNullable创建（参数可以是null值）
        String val = null;
        Optional<String> opt2 = Optional.ofNullable(val);
        Assert.assertFalse(opt2.isPresent());
    }

    /**
     * 场景2：常用方法使用
     * 1）orElse 可返回默认值：The orElse() method returns the wrapped value if it's present, and its argument otherwise
     * 2）orElseGet 不存在时，调用指定方法创建值：The orElseGet() method is similar to orElse(). However, instead of taking a value to return if the Optional value is not present,
     *              it takes a supplier functional interface, which is invoked and returns the value of the invocation
     * 3）orElseThrow 不存在值时，抛出异常：Instead of returning a default value when the wrapped value is not present, it throws an exception:
     */
    @Test
    public void test_common_method() {

        // orElse方法
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("john");
        System.out.println(name);

        // orElseGet方法
        String name2 = Optional.ofNullable(nullName).orElseGet(() -> "john2");
        System.out.println(name2);

        // orElseThrow方法
        try {
            Optional.ofNullable(nullName).orElseThrow(
                    IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
