package thinking.polymorphism.case5;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Sandwich extends PortableLunch {

    private Bread b = new Bread();

    private Cheese c = new Cheese();

    private Lettuce l = new Lettuce();

    public Sandwich() {
        System.out.println("Sandwich()");
    }

    public static void main(String[] args) { //构造器和多态
        new Sandwich();
    }
}
