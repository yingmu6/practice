package relative.basic.abstracttest;

/**
 * @author : chensy
 * Date : 2020/8/24 上午10:07
 */
public abstract class Animal { // 抽象类测试

    Animal() {
        System.out.println("这是抽象类");
    }

    abstract void cry();

    /**
     * 1）向上转型
     * 2）抽象类实现多态
     */
    public static void main(String[] args) { // test
        Animal animal = new Dog(); //会先执行父类的构造函数
        animal.cry();

        Animal animal2 = new Pig();
        animal2.cry();

        Animal animal3 = new Animal() {
            @Override
            void cry() {
                System.out.println("匿名类");
            }
        };
        animal3.cry();

    }

    //todo @csy-23/02/22 探索接口与实现类、子类与父类、实现类与抽象类的关系
}
