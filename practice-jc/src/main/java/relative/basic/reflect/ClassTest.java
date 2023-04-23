package relative.basic.reflect;

/**
 * @author : chensy
 * Date : 2020/10/27 下午5:23
 */
public class ClassTest {

    private boolean flag; //默认为false

    public static void main(String[] args) {
        System.out.println(1);
        int a = 2;
        System.out.println(Integer.valueOf(a).getClass());
        System.out.println(Integer.valueOf(a).getClass().getModifiers()); //输出17
//        System.out.println(Animal.values());

        //todo @csy-23/02/20 java.lang.Class#toGenericString方法测试

        ClassTest classTest = new ClassTest();
        System.out.println("default:" + classTest.isFlag());
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


    /**
     * 场景2：Method、Filed等使用
     * 1）Filed中的setAccessible(true)探索
     * 2）Filed的set(Object obj, Object value)方法使用，当obj=null时是什么含义？
     */

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

//todo @csy 枚举使用
//enum Animal {
//    private String name;
//    private int age;
//    Pig();
//    Dog();
//}
