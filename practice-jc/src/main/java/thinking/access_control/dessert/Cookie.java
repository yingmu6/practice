package thinking.access_control.dessert;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class Cookie {
    public Cookie() {
        System.out.println("Cookie constructor");
    }

    void bite() { //默认访问权限
        System.out.println("bite");
    }
}
