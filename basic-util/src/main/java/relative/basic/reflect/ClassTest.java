package relative.basic.reflect;

/**
 * @author : chensy
 * Date : 2020/10/27 下午5:23
 */
public class ClassTest {
    public static void main(String[] args) {
        System.out.println(1);
        int a = 2;
        System.out.println(Integer.valueOf(a).getClass());
        System.out.println(Integer.valueOf(a).getClass().getModifiers()); //输出17
//        System.out.println(Animal.values());
    }
}

//todo @csy 枚举使用
//enum Animal {
//    private String name;
//    private int age;
//    Pig();
//    Dog();
//}
