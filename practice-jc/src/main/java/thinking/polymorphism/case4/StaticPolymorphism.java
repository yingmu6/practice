package thinking.polymorphism.case4;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class StaticPolymorphism {
    public static void main(String[] args) { //某个方法是静态的，它的行为不具备多态性
        StaticSuper sup = new StaticSub();
        System.out.println(sup.staticGet());
        System.out.println(sup.dynamicGet());
    }
}
