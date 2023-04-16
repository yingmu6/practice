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

        //todo @csy-23/02/20 java.lang.Class#toGenericString方法测试
    }


    /**
     * 场景1：isAssignableFrom方法的使用
     * 1）https://www.cnblogs.com/greatfish/p/6097507.html
     * 2）https://www.baeldung.com/java-isinstance-isassignablefrom  与instance of的差异
     * 3）描述语言：
     * In other words, instanceof operator checks if the left object is same or subclass of right class,
     * while isAssignableFrom checks if we can assign object of the parameter class (from) to the reference of
     * the class on which the method is called.Note that both of these consider the actual instance not the reference type
     */
}

//todo @csy 枚举使用
//enum Animal {
//    private String name;
//    private int age;
//    Pig();
//    Dog();
//}
