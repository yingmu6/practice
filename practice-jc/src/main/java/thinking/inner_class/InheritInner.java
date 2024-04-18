package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class InheritInner extends WithInner.Inner {
    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);

        /**
         * （场景：内部类可以被覆盖吗）
         *
         * 输出结果：
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
