package thinking.exception;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/3
 */
public class ExtraFeatures {

    /**
     * 知识点（12.4.1）：异常与记录日志
     */
    static class MyException2 extends Exception {
        private int x;
        public MyException2() {}
        public MyException2(String msg) { super(msg); }
        public MyException2(String msg, int x) {
            super(msg);
            this.x = x;
        }
        public int val() { return x; }
        public String getMessage() {
            return "Detail Message：" + x + " " + super.getMessage();
        }
    }

    static void f() throws MyException2 {
        print("Throwing MyException2 from f()");
        throw new MyException2();
    }

    static void g() throws MyException2 {
        print("Throwing MyException2 from g()");
        throw new MyException2("Originated in g()");
    }

    static void h() throws MyException2 {
        print("Throwing MyException2 from h()");
        throw new MyException2("Originated in h()", 47);
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (MyException2 e) {
           e.printStackTrace(System.out);
        }

        try {
            g();
        } catch (MyException2 e) {
           e.printStackTrace(System.out);
        }

        try {
            h();
        } catch (MyException2 e) {
           e.printStackTrace(System.out);
           System.out.println("e.val() = " + e.val());
        }
    }
}
