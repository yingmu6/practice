package thinking.inner_class.case3;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MNA {
    private void f() {
        System.out.println("MNA f()");
    }

    class A {
        private void g() {
            System.out.println("A g()");
        }
        public class B {
            void h() {
                System.out.println("B h()");
                g();
                f();
            }
        }
    }
}
