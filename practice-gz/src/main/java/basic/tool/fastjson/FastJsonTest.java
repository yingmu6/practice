package basic.tool.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

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
    }

    @Getter
    @Setter
    static class Student {
        private String name;
        private int age;
    }
}
