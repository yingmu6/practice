package thinking.access_control;

import thinking.access_control.cookie2.Cookie;

/**
 * @author chensy
 * @date 2024/5/3
 */
public class ChocolateChip2 extends Cookie {

    /**
     * 知识点（6.2.4）：
     */
    public ChocolateChip2() {
        System.out.println("ChocolateChip2 constructor");
    }

    public void chomp() {
        bite(); //能访问Cookie中bite方法
    }

    public static void main(String[] args) {
        ChocolateChip2 x = new ChocolateChip2();
        x.chomp();
    }
}
