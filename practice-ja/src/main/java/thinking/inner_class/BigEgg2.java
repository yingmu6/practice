package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class BigEgg2 extends Egg2 {
    public class Yolk extends Egg2.Yolk {
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }

        public void fn() {
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2() {
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        e2.g();

        /**
         * （场景：内部类可以被覆盖吗）
         *
         * 输出结果：
         * Egg2.Yolk()
         * New Egg2()
         * Egg2.Yolk()
         * BigEgg2.Yolk()
         * BigEgg2.Yolk.f()
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
