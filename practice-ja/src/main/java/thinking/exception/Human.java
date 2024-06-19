package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Human {

    static class Annoyance extends Exception {}

    static class Sneeze extends Annoyance {}

    public static void main(String[] args) {
        try {
           throw new Sneeze();
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
        } catch (Annoyance a) {
            System.out.println("Caught Annoyance");
        }

        try {
            throw new Sneeze();
        } catch (Annoyance a) {
            System.out.println("Caught Annoyance");
        }
    }
}
