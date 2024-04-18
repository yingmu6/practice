package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class BigEgg extends Egg {

    class Egg {
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
    }
}
