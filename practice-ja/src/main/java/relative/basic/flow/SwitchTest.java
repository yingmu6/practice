package relative.basic.flow;

/**
 * @author : chensy
 * Date : 2020/8/3 上午11:29
 */
public class SwitchTest {
    public static void main(String[] args) {
        int type = 2;
        switch (type) {
            case 1:
                System.out.println(11);
            case 2:
                System.out.println(22); // 没有break，会继续执行后续的代码，并且不会匹配case值
            case 3:{
                System.out.println(type);
                break;
            }
            default:
                System.out.println("不匹配");
        }
    }
}

/**
 * 若没有break，则继续执行
 *  程序继续执行后面的case语句代码和default，直到遇见break或者右花括号
 *  此处的结果为
 *  22
 *  2
 */
