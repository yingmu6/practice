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
}
