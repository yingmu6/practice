package relative.basic.variable;

/**
 * 共享变量和私有变量
 * @author : chensy
 * Date : 2020/11/5 上午11:25
 */
public class Test {
    public static void main(String[] args) {
        Animal animal = new Animal(1);
        Animal animal2 = new Animal(2);
        System.out.println(animal.getAge() + ";" + Animal.weight);
        System.out.println(animal2.getAge() + ";" + Animal.weight);
    }
}

class Animal {
    private int age; //成员变量
    protected static double weight; //类变量

    public Animal(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
