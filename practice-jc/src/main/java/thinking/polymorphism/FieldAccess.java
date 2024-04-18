package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class FieldAccess {
    static class Super {
        public int field =1;
        public int getField() { return field; }
    }

    static class Sub extends Super {
        public int field = 1;
        public int getField() { return field; }
        public int getSuperField() { return super.field; }
    }


    public static void main(String[] args) { //缺陷：域与静态方法
        Super sup = new Sub();
        System.out.println("sup.field = " + sup.field
                + "，sup.getField() = " + sup.getField());

        Sub sub = new Sub();
        System.out.println("sub.field = " + sub.field + "，sub.getField() = "
                + sub.getField() + "，sub.getSuperField() = " + sub.getSuperField());
    }
}
