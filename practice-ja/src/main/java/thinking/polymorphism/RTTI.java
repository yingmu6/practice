package thinking.polymorphism;

/**
 * @author orange
 * @date 2024/6/12
 */
public class RTTI {

    /**
     * 知识点（8.5.2）：运行时类型识别
     */

    static class Useful {
        public void f() {}
        public void g() {}
    }

    static class MoreUseful extends Useful {
        public void f() {}
        public void g() {}
        public void u() {}
        public void v() {}
        public void w() {}
    }

    public static void main(String[] args) {
        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };
        x[0].f();
        x[1].g();

        //! x[1].u();
        ((MoreUseful)x[1]).u();
        ((MoreUseful)x[0]).u();
    }
}
