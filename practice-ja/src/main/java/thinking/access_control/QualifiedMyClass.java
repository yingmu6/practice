package thinking.access_control;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class QualifiedMyClass {

    /**
     * 知识点（6.1.1）：代码组织
     */
    public static void main(String[] args) {
        thinking.access_control.mypackage.MyClass m =
                new thinking.access_control.mypackage.MyClass(); //指定完整的类名
    }
}
