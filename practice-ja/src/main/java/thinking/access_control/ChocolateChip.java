package thinking.access_control;

import thinking.access_control.dessert.Cookie;

/**
 * @author chensy
 * @date 2024/5/3
 */
public class ChocolateChip extends Cookie {

    /**
     * 知识点（6.2.4）：protected继承访问权限
     */
    public ChocolateChip() {
        System.out.println("ChocolateChip constructor");
    }

    public void chomp() {
        // bite(); //不能访问bite()方法
    }

    public static void main(String[] args) {
        ChocolateChip x = new ChocolateChip();
        x.chomp();

        /**
         * 输出结果：
         * Cookie constructor
         * ChocolateChip constructor
         *
         * 结果分析：
         */
    }
}
