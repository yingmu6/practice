package basic.tool.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * fast json测试
 * @Author chenSy
 * @Date 2022/12/07 19:54
 * @Description
 */
public class FastJsonTest {
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


    @Getter
    @Setter
    static class Student {
        private String name;
        private int age;
    }
}
