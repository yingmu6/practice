package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public interface ClassInInterface {

    void howdy();

    class Test implements ClassInInterface { //接口内部的类

        @Override
        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();

            /**
             * 输出结果：
             * Howdy!
             *
             * 结果分析：
             *
             * 总结概括：
             */
        }
    }
}