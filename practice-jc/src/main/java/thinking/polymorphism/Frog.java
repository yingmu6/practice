package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class Frog extends FrogExt.Amphibian {
    private FrogExt.Characteristic p = new FrogExt.Characteristic("Croaks");
    private FrogExt.Description t = new FrogExt.Description("Eats Bugs");

    public Frog() {
        System.out.println("Frog()");
    }

    @Override
    protected void dispose() {
        System.out.println("Frog dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }

    public static void main(String[] args) { //继承与清理
        Frog frog = new Frog();
        System.out.println("Bye!");
        frog.dispose();
    }
}
