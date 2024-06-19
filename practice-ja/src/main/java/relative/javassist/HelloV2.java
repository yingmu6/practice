package relative.javassist;

/**
 * @Author chenSy
 * @Date 2023/04/17 16:41
 * @Description
 */
public class HelloV2 {

    private String greet = "默认值";

    public void sayV2() {
        System.out.println("Hello V2");
    }

    public void sayHello(String greet) {
        this.greet = greet;
        System.out.println(greet + "！！");
    }
}
