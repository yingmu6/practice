package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class MethodInit {

    int i = f(); //调用某个方法提供初值

    int f() {
        return 11;
    }
}
