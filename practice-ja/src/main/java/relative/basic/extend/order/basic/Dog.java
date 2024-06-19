package relative.basic.extend.order.basic;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chensy
 * @date 2022/4/26
 */
@Getter
@Setter
public class Dog extends Animal {

    private String run;

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setRun("ssss");
        Animal animal = dog;

        System.out.println(animal.getCry() + ",,,"); // 父类只能访问，自身拥有的方法，不能访问子类新增的方法
        System.out.println(dog.getRun() + ",,," + dog.getCry());

    }
}
