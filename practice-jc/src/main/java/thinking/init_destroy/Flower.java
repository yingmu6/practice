package thinking.init_destroy;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class Flower {

    /**
     * 知识点（5.4）：在构造器中调用构造器
     */
    int petalCount = 0;

    String s = "initial value";

    Flower(int petals) {
        petalCount = petals;
        print("Constructor w/ int arg only，petalCount = " + petalCount);
    }

    Flower(String ss) {
        print("Constructor w/ String arg only，s = " + ss);
        s = ss;
    }

    Flower(String s, int petals) {
        this(petals);
        // this(s); this语句需要在第一行
        this.s = s;
        print("String & int args");
    }

    Flower() {
       this("hi", 47);
       print("default constructor (no args)");
    }

    void printPetalCount() {
        // this(11); 通过this调用构造方法，不能放在非构造方法中
    }

    public static void main(String[] args) {
        Flower x = new Flower();
        x.printPetalCount();
    }
}
