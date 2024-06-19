package relative.basic.switch_test;

/**
 * @author chensy
 * @date 2022/3/30
 * <p>
 * https://www.runoob.com/java/java-switch-case.html  Java switch case 语句
 */
public class SwitchTest {
    public static void main(String[] args) {
        int a = 2;
        switch (a) {
            case 1:
                System.out.println(11);
            case 2:
                System.out.println(22); //没有break，后面都会执行
            default:
                System.out.println(33);
        }
    }
}
