package thinking.concurrent.case5;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class SerialNumberGenerator {

    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++;
    }
}
