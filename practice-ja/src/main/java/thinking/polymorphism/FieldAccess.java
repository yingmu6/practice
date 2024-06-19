package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class FieldAccess { //@TkY-Doing

    /**
     * 知识点：域与静态方法
     *
     * 知识点概括：
     * 1）
     */

    static class Super {
        public int field = 1;
        public int getField() {
            return field;
        }
    }

    static class Sub extends Super {
        public int field = 2;
        public int getField() {
            return field;
        }
        public int getSuperField() {
            return super.field;
        }
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
         * 1）向上转型中，sup.field访问的是父类的字段，而sup.getField()访问的是子类重写的方法
         *
         * 2）通过子类引用访问到的字段，是子类的字段；若想访问父类的字段，需要使用super
         */
    }
}
