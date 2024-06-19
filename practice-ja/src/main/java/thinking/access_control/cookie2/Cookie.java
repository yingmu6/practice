package thinking.access_control.cookie2;

/**
 * @author chensy
 * @date 2024/5/3
 */
public class Cookie {

    /**
     * 知识点（6.2.4）：protected继承访问权限
     */
    public Cookie() {
        System.out.println("Cookie constructor");
    }

    protected void bite() {
        System.out.println("bite");
    }
}
