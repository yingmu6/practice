package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class DetergentExt {
    /**
     * 特别说明：
     * 避免循环继承，另外创建Ext类（会抛出异常：Cyclic inheritance involving 'xxx.xxx'）
     * 循坏继承在java中是不允许的，不仅仅局限于接口，类也不能循环继承。因为编译的时候，编译器不知道该先编译哪个。
     */

    /**
     * 知识点（7.2）：继承语法
     */
    static class Cleanser {
        private String s = "Cleanser";
        public void append(String a) { s += a; }
        public void dilute() { append(" dilute() "); }
        public void apply() { append(" apply() "); }
        public void scrub() { append(" scrub() "); }
        public String toString() { return s; }

        public static void main(String[] args) {
            Cleanser x = new Cleanser();
            x.dilute();
            x.apply();
            x.scrub();
        }
    }

    static public class Detergent extends Cleanser {
        public void scrub() {
            append(" Detergent.scrub() ");
            super.scrub();
        }

        public void foam() {
            append(" foam() ");
        }

        public static void main(String[] args) {
            Detergent x = new Detergent();
            x.dilute();
            x.apply();
            x.scrub();
            x.foam();
            print(x);
            print("Testing base class：");
            Cleanser.main(args);
        }
    }
}
