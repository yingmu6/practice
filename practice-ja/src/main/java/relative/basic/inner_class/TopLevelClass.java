package relative.basic.inner_class;

/**
 * @Author chenSy
 * @Date 2023/05/12 16:43
 * @Description
 */
public class TopLevelClass {
    void accessMembers(OuterClass outer) {
        // Compiler error: Cannot make a static reference to the non-static
        //     field OuterClass.outerField
        // System.out.println(OuterClass.outerField);

        System.out.println(outer.outerField); //访问外部类的成员变量
        System.out.println(OuterClass.staticOuterField);
    }
}
