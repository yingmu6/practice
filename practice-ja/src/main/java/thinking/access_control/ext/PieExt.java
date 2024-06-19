package thinking.access_control.ext;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class PieExt {
    void f() {
        System.out.println("ext.Pie f()");
    }

    private void h() {
        System.out.println("ext.Pie h()");
    }

    public void g() {
        System.out.println("ext.Pie g()");
    }

    public void accessH() {
        h();
    }
}
