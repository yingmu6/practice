package thinking.polymorphism;


/**
 * @author chensy
 * @date 2024/3/29
 */
public class SandwichExt {

    static class Meal {
        Meal() {
            System.out.println("Meal()");
        }
    }

    static class Bread {
        Bread() {
            System.out.println("Bread()");
        }
    }

    static class Cheese {
        Cheese() {
            System.out.println("Cheese()");
        }
    }

    static class Lettuce {
        Lettuce() {
            System.out.println("Lettuce()");
        }
    }

    static class Lunch extends Meal {
        Lunch() {
            System.out.println("Lunch()");
        }
    }

    static class PortableLunch extends Lunch {
        PortableLunch() {
            System.out.println("PortableLunch()");
        }
    }
}
