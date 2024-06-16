package interview.written_exam.basic.polymor_ext;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class Pa {

    static {
        System.out.println("Pa static block");
    }

    public static void prt() {
        System.out.println("1");
    }

    public Pa() {
        System.out.println("Pa constructor method");
    }
}
