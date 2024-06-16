package interview.offer_come.basic.reflect;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chensy
 * @date 2024/3/23
 */
@Setter
@Getter
public class Person {

    private String name;

    private Integer age;

    private String address;

    public Person() {};
    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
