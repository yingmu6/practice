package thinking.interface_relative.nesting;

/**
 * @author orange
 * @date 2024/6/5
 */
public class A {

    interface B {
        void f();
    }

    public class BImp implements B {
        public void f() {}
    }

    private class BImp2 implements B {
        public void f() {}
    }

    public interface C {
        void f();
    }

    class CImp implements C {
        public void f() {}
    }

    private interface D {
        void f();
    }

    private class DImp implements D {
        public void f() {}
    }

    private class DImp2 implements D {
        public void f() {}
    }

    public D getD() { return new DImp2(); }
    private D dRef;
    public void receiveD(D d) {
        dRef = d;
        dRef.f();
    }
}
