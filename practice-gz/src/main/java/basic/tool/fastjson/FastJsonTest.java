package basic.tool.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * fast json测试
 * @Author chenSy
 * @Date 2022/12/07 19:54
 * @Description
 */
public class FastJsonTest { //@GzY-Doing
    public static void main(String[] args) {

        String text = "{\"name\":\"zhangsan\",\"age\":11}";

        JSONObject obj = JSON.parseObject(text);
        System.out.println(obj);

        Student student = JSON.parseObject(text, Student.class);
        System.out.println(student.getName() + ",,," + student.getAge());

        listToJsonArray();
        listToJsonArrayWithNull();
    }

    public static void listToJsonArray() {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(11);

        Student s2 = new Student();
        s2.setName("李四");
        s2.setAge(12);

        students.add(s1);
        students.add(s2);

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(students));
        System.out.println("列表转数组=" + array);
    }

    public static void listToJsonArrayWithNull() {
        List<Student> students = new ArrayList<>();

        JSONArray array = JSONArray.parseArray(JSON.toJSONString(students));
        System.out.println("列表转数组，空列表=" + array);

        List<Student> students1 = null;
        // 不会抛空指针异常，JSON内部处理了，返回Null值
        JSONArray array1 = JSONArray.parseArray(JSON.toJSONString(students1));
        System.out.println("列表转数组，null值=" + array1);
    }


    @Test
    public void test_by_class() { //Done_获取指定Class对应的JSON对象

        String teacherStr = "{\"userName\":\"张老师\",\"userAge\":25}";
        Class<? extends AbstractUser> teacherClass = getUser("teacher");

        AbstractUser abstractUser = JSON.parseObject(teacherStr, teacherClass); //解析指定Class的对象
        Assert.assertTrue(abstractUser instanceof Teacher);
        System.out.println(abstractUser);

        String teacherStr2 = "{\"data\":{\"userName\":\"李老师\",\"userAge\":28}}";
        JSONObject jsonObject = JSON.parseObject(teacherStr2);
        AbstractUser abstractUser1 = jsonObject.getObject("data", Teacher.class); //获取指定Class的对象
        Assert.assertTrue(abstractUser1 instanceof Teacher);
        System.out.println(abstractUser1);

        /**
         * 输出结果：
         * Teacher：name = 张老师，age = 25
         * Teacher：name = 李老师，age = 28
         *
         * 结果分析：
         * 1）在解析和获取JSON对象时，可以指定对应的Class，即可获取对应的实例
         */
    }

    private Class<? extends AbstractUser> getUser(String type) {
        if (type.equals("teacher")) {
            return Teacher.class;
        } else {
            return Staff.class;
        }
    }

    @Getter
    @Setter
    static class Student {
        private String name;
        private int age;
    }

    static class Teacher extends AbstractUser {

        @Override
        public String toString() {
            return "Teacher：name = " + getUserName() + "，age = " + getUserAge();
        }
    }

    static class Staff extends AbstractUser {

        @Override
        public String toString() {
            return "Staff：name = " + getUserName() + "，age = " + getUserAge();
        }
    }

    @Getter
    @Setter
    static abstract class AbstractUser {
        private String userName;
        private int userAge;
    }
}
