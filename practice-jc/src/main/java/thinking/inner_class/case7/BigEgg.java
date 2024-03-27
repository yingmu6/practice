package thinking.inner_class.case7;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class BigEgg extends Egg {

    public class Yolk {
        public Yolk() {
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();

        /**
         * （场景：内部类可以被覆盖吗）
         *
         * 输出结果：
         * New Egg()
         * Egg.Yolk()
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
