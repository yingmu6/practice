package relative.basic.array_test;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * 使用System拷贝数据元素
 *
 * @author chensy
 * @date 2022/5/8
 */
public class ArrayTest {
    public static void main(String[] args) {
//        changeValue();
        assignment();
    }

    public static void basicUse() {
        String[] sourceArr = {"11", "22", "33"};
        String[] targetArr = new String[4];

        System.arraycopy(sourceArr, 1, targetArr, 0, 2);
        System.out.println(JSON.toJSONString(targetArr)); //输出：["22","33",null,null]
    }

    public static void changeValue() {
        String[] sourceArr = {"11", "22", "33"};
        String str = sourceArr[1];

        sourceArr[1] = "aaa"; //对数组的元素设置了新的值
        System.out.println("老的值:" + str +",新的值："+sourceArr[1]);
    }

    private static void assignment() { //对象赋值
        Student[] students = new Student[3];
        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(12);

        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(13);

        students[0] = student1;

        /**
         * 先区分引用对象，再判断是否是相同的引用值变更（同一个对象值变更了，关联引用也对应变更）
         * 1）old与student[0] 是同一个对象，所以不管更改哪个引用，另外一个引用对应的值也会变更
         * 2）students[0]与student2 是同一个引用
         */
        Student old = students[0]; //进行对象赋值后，old指向student[0]引用，比如student[0]为ArrayTest$Student498，赋值后old也是指向ArrayTest$Student498
        students[0] = student2;  //进行对象赋值后，students[0]指向students[2]引用，比如student[2]为ArrayTest$Student499，赋值后students[0]也是指向ArrayTest$Student499

        System.out.println("老的值：" + JSON.toJSONString(old) + ",新的值："+JSON.toJSONString(students[0]));

        old.setName("hhhh");
        System.out.println("引用1：" + JSON.toJSONString(old) + ",引用2："+JSON.toJSONString(student1));

        students[0].setName("hhyyy");
        System.out.println("引用3：" + JSON.toJSONString(students[0]) + ",引用4："+JSON.toJSONString(student2));
    }

    @Getter
    @Setter
    static class Student {
        private String name;
        private Integer age;
    }
}

