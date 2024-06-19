package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class TestBed {

    public void fn() {
        System.out.println("f()");
    }

    public static class Tester {

        public static void main(String[] args) {
            TestBed t = new TestBed();
            t.fn();

            /**
             * （场景：接口内部的类）
             *
             * 输出结果：
             * f()
             *
             * 结果分析：
             *
             * 总结概括：
             */
        }

    }
}
