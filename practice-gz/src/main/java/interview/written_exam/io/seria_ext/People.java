package interview.written_exam.io.seria_ext;

import java.io.Serializable;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class People implements Serializable {

    private static final long serialVersionUID = -3110956137962256666L;

    private String name;

    private int age;

    public People() {
        this.name = "lili";
        this.age = 20;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
