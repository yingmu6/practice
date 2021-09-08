package relative.basic.abstracttest;

/**
 * @author : chensy
 * Date : 2020/8/24 上午10:07
 */
public abstract class Animal { //
    abstract void cry();

    /**
     * 1）向上转型
     * 2）抽象类实现多态
     */
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.cry();

        Animal animal2 = new Pig();
        animal2.cry();
    }
}
