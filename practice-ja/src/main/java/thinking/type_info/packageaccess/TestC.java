package thinking.type_info.packageaccess;

/**
 * @author orange
 * @date 2024/7/6
 */
public class TestC { //@TkY-Done
    /**
     * 知识点：默认类的访问
     */
    public static void main(String[] args) { //Done
        C c = new C();
        c.f();
        c.g();

        /**
         * 结果分析：
         * 1）能访问HiddenC中的类C，但是C在包以外就直接访问不了
         */
    }
}
