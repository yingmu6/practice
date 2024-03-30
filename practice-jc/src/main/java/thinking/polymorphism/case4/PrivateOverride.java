package thinking.polymorphism.case4;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class PrivateOverride {

    private void fn() {
        System.out.println("private fn()");
    }

    public static void main(String[] args) { //缺陷："覆盖"私有方法
        PrivateOverride po = new Derived();
        po.fn();
    }
}
