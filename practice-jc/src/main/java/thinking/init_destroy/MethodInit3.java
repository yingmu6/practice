package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class MethodInit3 {

    // int j = g(i); 报"非法向前引用"的警告，因为i还没初始化，就直接使用

    int i = f();

    int f() {
        return 11;
    }

    int g(int n) {
        return n * 10;
    }
}
