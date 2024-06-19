package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MultiImplementation {

    static class D {
        public D() {
            System.out.println("I am D");
        }
    }

    static abstract class E {
    }

    static class Z extends D {
        E makeE() {
            return new E() {}; //返回匿名实现类
        }
    }

    static void takesD(D d) {}

    static void takesE(E e) {}

    public static void main(String[] args) {
        Z z = new Z();
        MultiImplementation.takesD(z);
        MultiImplementation.takesE(z.makeE());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }
}
