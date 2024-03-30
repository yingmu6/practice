package thinking.polymorphism.case7;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Composing {

    private Shared shared;

    private static long counter = 0;

    private final long id = counter++;

    public Composing(Shared shared) {
        System.out.println("Creating " + this);
        this.shared = shared;
        this.shared.addRef();
    }

    protected void dispose() {
        System.out.println("disposing " + this);
        shared.dispose();
    }

    public String toString() {
        return "Composing " + id;
    }
}
