package relative.basic.inner_class;

/**
 * @Author chenSy
 * @Date 2023/05/12 16:40
 * @Description
 */
public class OuterClass { //外部类

    String outerField = "Outer field";
    static String staticOuterField = "Static outer field";

    private String outerPriField = "Outer private field";

    class InnerClass { //内部类
        void accessMembers() {
            System.out.println(outerField);
            System.out.println(staticOuterField);
            System.out.println(outerPriField);
        }
    }

    static class StaticNestedClass { //静态内部类
        void accessMembers(OuterClass outer) {
            // Compiler error: Cannot make a static reference to the non-static
            //     field outerField
            // System.out.println(outerField);
            System.out.println(outer.outerField);
            System.out.println(staticOuterField);
        }
    }
}
