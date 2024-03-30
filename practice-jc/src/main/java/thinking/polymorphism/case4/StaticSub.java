package thinking.polymorphism.case4;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class StaticSub extends StaticSuper {

    public static String staticGet() {
        return "Derived staticGet()";
    }

    public String dynamicGet() {
        return "Derived dynamicGet()";
    }
}
