package thinking.access_control;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class Pie {
    void f() {
        System.out.println("Pie.f()");
    }

    private void h() {
        System.out.println("Pie.h()");
    }

    public void g() {
        System.out.println("Pie.g()");
    }

    public void accessH() {
        h();
    }
}
