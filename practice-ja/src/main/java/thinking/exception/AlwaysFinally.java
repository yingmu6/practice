package thinking.exception;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class AlwaysFinally {

    /**
     * 知识点（12.8.1）：finally用途
     */
    static class FourException extends Exception {}

    public static void main(String[] args) {
        print("Entering first try block");
        try {
            print("Entering second try block");
            try {
                throw new FourException();
            } finally {
                print("finally in 2nd try block");
            }
        } catch (FourException e) {
            System.out.println("Caught FourException in 1st try block");
        } finally {
            System.out.println("finally in 1st try block");
        }
    }
}
