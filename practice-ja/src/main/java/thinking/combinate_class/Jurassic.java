package thinking.combinate_class;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class Jurassic {

    /**
     * 知识点（7.8）：final类
     */
    static class SmallBrain {}

    static final class Dinosaur {
        int i = 7;
        int j = 1;
        SmallBrain x = new SmallBrain();
        void f() {}
    }

    public static void main(String[] args) {
        Dinosaur n = new Dinosaur();
        n.f();
        n.i = 40;
        n.j ++;
    }
}
