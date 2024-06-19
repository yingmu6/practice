package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class StaticPolymorphism {

    static class StaticSuper {
        public static String staticGet() {
            return "Base staticGet";
        }

        public String dynamicGet() {
            return "Base dynamicGet()";
        }
    }

    static class StaticSub extends StaticSuper {
        public static String staticGet() {
            return "Derived staticGet()";
        }
        public String dynamicGet() {
            return "Derived dynamicGet()";
        }
    }

    public static void main(String[] args) { //某个方法是静态的，它的行为不具备多态性
        StaticSuper sup = new StaticSub();
        System.out.println(sup.staticGet());
        System.out.println(sup.dynamicGet());
    }
}
