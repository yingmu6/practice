package thinking.inner_class.basic;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class AnonymousConstructor {

    public static Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("Inside instance initializer");
            }
            @Override
            public void fn() {
                System.out.println("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.fn();

        /**
         * 输出结果：
         * Base constructor，i =47
         * Inside instance initializer
         * In anonymous f()
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
