package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class PrivateOverride {

    static class Derived extends PrivateOverride {
        public void fn() {
            System.out.println("public fn()");
        }
    }

    private void fn() {
        System.out.println("private fn()");
    }

    public static void main(String[] args) { //缺陷："覆盖"私有方法
        PrivateOverride po = new Derived();
        po.fn();
    }
}
