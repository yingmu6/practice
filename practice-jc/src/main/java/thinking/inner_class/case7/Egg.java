package thinking.inner_class.case7;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Egg {

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
