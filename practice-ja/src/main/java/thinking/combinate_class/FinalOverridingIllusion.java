package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class FinalOverridingIllusion {

    /**
     * 知识点（7.8）：final方法
     */

    static class WithFinals {
        private final void fn() { print("WithFinals.f()"); }
        private void g() { print("WithFianls.g()"); }
    }

    static class OverridingPrivate extends WithFinals {
        private final void f() {
            print("OverridingPrivete.f()");
        }

        private void g() {
            print("OverringPrivate.g()");
        }
    }

    static class OverridingPrivate2 extends OverridingPrivate {
        public final void f() {
            print("OverridingPrivate2.f()");
        }
        public final void g() {
            print("OverridingPrivate2.g()");
        }
    }

    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();

        OverridingPrivate op = op2;
        WithFinals wf = op2;
    }
}
