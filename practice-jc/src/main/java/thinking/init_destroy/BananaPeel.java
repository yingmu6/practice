package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class BananaPeel {

    static class Banana { void peel(int i) {} }

    public static void main(String[] args) {
        Banana a = new Banana(),
               b = new Banana();
        a.peel(1);
        b.peel(2);
    }
}
