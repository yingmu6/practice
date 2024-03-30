package thinking.polymorphism.case4;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class FieldAccess {
    public static void main(String[] args) { //缺陷：域与静态方法
        Super sup = new Sub();
        System.out.println("sup.field = " + sup.field
                + "，sup.getField() = " + sup.getField());

        Sub sub = new Sub();
        System.out.println("sub.field = " + sub.field + "，sub.getField() = "
                + sub.getField() + "，sub.getSuperField() = " + sub.getSuperField());
    }
}
