package thinking.type_info;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class SweetShop {

    /**
     * 知识点：Class对象
     */

    static class Gum {
        static {
            System.out.println("Loading Gum");
        }
    }

    static class Cookie {
        static {
            System.out.println("Loading Cookie");
        }
    }

    static class Candy {
        static {
            System.out.println("Loading Candy");
        }
    }

    public static void main(String[] args) {
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        try {
            Class.forName("Gum");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find Gum");
        }
        new Cookie();
        System.out.println("After creating Cookie");
    }
}
