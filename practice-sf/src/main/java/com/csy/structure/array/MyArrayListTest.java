package com.csy.structure.array;

import com.csy.structure.array.ext.MyArrayList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chensy
 * @date 2023/9/5
 */
public class MyArrayListTest {

    /**
     * 自定义ArrayList_测试
     * 1）MyArrayList内部维护的结构为数组，在添加时会判断是否要扩容
     *
     * 2）ArrayList的底层原理
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void test_my_array_list() {
        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.indexOf(4));
        System.out.println(list.contain(2));
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }

        /**
         * 输出结果：
         *
         * -1
         * true
         * 1
         * 2
         * 3
         *
         * 结果分析：
         * 1）indexOf(Object o)：是查找对象在列表中的位置，若没有找到返回-1（内部使用equals比较对象）
         * 2）contain(Object obj)：判断对象是否在列表中，内部会遍历列表，依次使用equals进行比较
         * 3）getSize()：获取列表的元素个数，列表内部维护着size变量，每添加一个元素都会加1
         * 4）get(int index)：获取指定下标的元素，由于列表中维护的是数组，所以可以直接通过下标访问数组元素
         * 5）add(Object o)：底层最终调用add(int index，Object o)，该方法在数组尾部添加元素
         */
    }

    /**
     * 场景2：测试添加
     */
    @Test
    public void test_my_array_list_add() {
        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 6);
        list.add(1, 7);

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }

        /**
         * 输出结果：
         * 1
         * 7
         * 6
         * 2
         * 3
         *
         * 结果分析：
         */
    }

    /**
     * 场景3：列表初始化
     */
    @Test
    public void test_list_init() { //Done

        // 方式一：使用Arrays.asList()初始化
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println("方式一：" + list);

        // 方式二：使用Collections的nCopies()拷贝
        List<String> list2 = new ArrayList<>(Collections.nCopies(3, "haha"));

        System.out.println("方式二：" + list2);
        // 方式三：使用Java 8的Stream API初始化
        ArrayList<Integer> list3 = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toCollection(ArrayList::new));

        System.out.println("方式三：" + list3);

        /**
         * 输出结果：
         * 方式一：[a, b, c]
         * 方式二：[haha, haha, haha]
         * 方式三：[1, 2, 3, 4, 5]
         *
         */

    }

    /**
     * 场景4：java8将list映射为Map
     */
    @Test
    public void test_list_to_map() { //DOING
        List<TestVO> testVOS = Arrays.asList(TestVO.builder().name("张三").age(11).build(),
                TestVO.builder().name("李四").age(12).build(),
                TestVO.builder().name("王五").age(12).build());

       // Map<Integer, String> tempMap = testVOS.stream().collect(Collectors.toMap(item::))
    }

    @Setter
    @Getter
    @Builder
    static class TestVO {
        private String name;
        private Integer age;
    }
}
