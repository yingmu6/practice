package thinking.polymorphism.case6;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Frog extends Amphibian {

    private Characteristic p = new Characteristic("Croaks");

    private Description t = new Description("Eats Bugs");

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
