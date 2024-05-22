package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class FieldAccess { //@TkY-Doing

    /**
     * 知识点：域与静态方法
     */

    static class Super {
        public int field = 1;
        public int getField() { return field; }
    }

    static class Sub extends Super {
        public int field = 2;
        public int getField() { return field; }
        public int getSuperField() { return super.field; }
    }


    public static void main(String[] args) {
        Super sup = new Sub();
        System.out.println("sup.field = " + sup.field
                + "，sup.getField() = " + sup.getField());

        Sub sub = new Sub();
        System.out.println("sub.field = " + sub.field + "，sub.getField() = "
                + sub.getField() + "，sub.getSuperField() = " + sub.getSuperField());

        /**
         * 输出结果：
         * sup.field = 1，sup.getField() = 2
         * sub.field = 2，sub.getField() = 2，sub.getSuperField() = 1
         *
         * 结果分析：
         */
    }
}
