package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class PassingThis {

    /**
     * 知识点（5.4）：this关键字
     */

    static class Person {
        public void eat(Apple apple) {
            Apple peeled = apple.getPeeled();
            System.out.println("Yummy");
        }
    }

    static class Peeler {
        static Apple peel(Apple apple) {
            return apple;
        }
    }

    static class Apple {
        Apple getPeeled() { return Peeler.peel(this);}
    }

    public static void main(String[] args) {
        new Person().eat(new Apple());
    }
}
