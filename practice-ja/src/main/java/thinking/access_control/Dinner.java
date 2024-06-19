package thinking.access_control;

import thinking.access_control.dessert.Cookie;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class Dinner {

    /**
     * 知识点（6.2.2）：public接口访问权限
     */
    public static void main(String[] args) {
        Cookie x = new Cookie();
        // x.bite(); //bite()方法不是public，而Dinner与Cookie也不在同一个包，所以访问不了
    }
}
