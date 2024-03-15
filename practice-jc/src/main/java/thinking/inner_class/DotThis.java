package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class DotThis {

    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            return DotThis.this;
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dtj = dt.inner();
        dtj.outer().f();

        /**
         * 输出结果：
         * DotThis.f()
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
