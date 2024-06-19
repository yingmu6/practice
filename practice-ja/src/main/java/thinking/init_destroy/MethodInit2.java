package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class MethodInit2 {

    int i = f();

    int j = g(i); //调用某个方法来提供初值，方法可以带参数，但这些参数必须是已经被初始化的

    int f() {
        return 11;
    }

    int g(int n) {
        return n * 10;
    }
}
