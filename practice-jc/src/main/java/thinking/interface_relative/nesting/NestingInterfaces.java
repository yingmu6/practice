package thinking.interface_relative.nesting;

/**
 * @author orange
 * @date 2024/6/5
 */
public class NestingInterfaces {

    /**
     * 知识点（9.8）：嵌套接口
     */

    public class BImp implements A.B {
        public void f() {}
    }

    class CImp implements A.C {
        public void f() {}
    }

    class EImp implements E {
        public void g() {}
    }

    class EGImp implements E.G {
        public void f() {}
    }

    class EImp2 implements E {
        public void g() {}
        class EG implements E.G {
            public void f() {}
        }
    }

    public static void main(String[] args) {
        A a = new A();
        A a2 = new A();
        a2.receiveD(a.getD());
    }

}
