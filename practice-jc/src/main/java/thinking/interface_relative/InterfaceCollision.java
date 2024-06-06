package thinking.interface_relative;

/**
 * @author orange
 * @date 2024/6/5
 */
public class InterfaceCollision {

    /**
     * 知识点（9.5.1）：组合接口时的名字冲突
     */
    interface I1 { void f(); }
    interface I2 { int f(int i); }
    interface I3 { int f(); }

    class C { public int f() { return 1; } }

    class C2 implements I1, I2 {
        public void f() {}
        public int f(int i) { return 1; }
    }

    class C3 extends C implements I2 {
        public int f(int i) { return 1; }
    }

    class C4 extends C implements I3 {
        public int f() { return 1; }
    }
}
