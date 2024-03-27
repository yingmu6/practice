package thinking.inner_class.case3;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MNA {
    private void f() {}

    class A {
        private void g() {}
        public class B {
            void h() {
                g();
                f();
            }
        }
    }
}
