package thinking.interface_relative.nesting;

/**
 * @author orange
 * @date 2024/6/5
 */
public interface E {
    interface G {
        void f();
    }

    public interface H {
        void f();
    }

    void g();
}