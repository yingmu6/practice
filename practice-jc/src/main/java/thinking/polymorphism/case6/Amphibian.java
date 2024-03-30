package thinking.polymorphism.case6;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Amphibian extends Animal {

    private Characteristic p = new Characteristic("can live in water");

    private Description t = new Description("Both water and land");

    Amphibian() {
        System.out.println("Amphibian()");
    }

    @Override
    protected void dispose() {
        System.out.println("Amphibian dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }
}
