package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class Sandwich extends SandwichExt.PortableLunch {

    private SandwichExt.Bread b = new SandwichExt.Bread();

    private SandwichExt.Cheese c = new SandwichExt.Cheese();

    private SandwichExt.Lettuce l = new SandwichExt.Lettuce();

    public Sandwich() {
        System.out.println("Sandwich()");
    }

    public static void main(String[] args) { //构造器和多态
        new Sandwich();
    }
}
