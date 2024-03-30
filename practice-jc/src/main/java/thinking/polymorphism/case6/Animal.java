package thinking.polymorphism.case6;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Animal extends LivingCreature {

    private Characteristic p = new Characteristic("has heart");

    private Description t = new Description("Animal not Vegetable");

    Animal() {
        System.out.println("Animal()");
    }

    protected void dispose() {
        System.out.println("Animal dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }
}
