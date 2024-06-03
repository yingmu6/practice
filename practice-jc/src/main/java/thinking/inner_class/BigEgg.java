package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class BigEgg extends Egg { //@TkY-Doing

    /**
     * 知识点：
     *
     * 问题点答疑：
     * 1）都有哪些类型的内部类？
     * 2）内部类的功能用途有哪些？
     */
    class Egg { //此处Egg和继承的Egg关系是怎样的？本意是什么？
        protected Yolk y;
        protected class Yolk {
            public Yolk() {
                System.out.println("Egg.Yolk()");
            }
        }

        public Egg() {
            System.out.println("New Egg()");
            y = new Yolk();
        }
    }

    public class Yolk {
        public Yolk() {
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();

        /**
         * 输出结果：
         * New Egg()
         * Egg.Yolk()
         *
         * 结果分析：
         * 1）
         */
    }
}
