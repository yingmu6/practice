package thinking.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class TurnOffChecking {

    /**
     * 知识点（12.12.4）：把"被检查的异常"转换为"不检查异常"
     */

    static class WrapCheckedException {
        void throwRuntimeException(int type) {
            try {
                switch (type) {
                    case 0: throw new FileNotFoundException();
                    case 1: throw new IOException();
                    case 2: throw new RuntimeException("Where am I?");
                    default: return;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class SomeOtherException extends Exception {}

    public static void main(String[] args) {
        WrapCheckedException wce = new WrapCheckedException();
        wce.throwRuntimeException(3);
        for (int i = 0; i < 4; i++) {
            try {
                if (i < 3) {
                    wce.throwRuntimeException(i);
                } else {
                    throw new SomeOtherException();
                }
            } catch (SomeOtherException e) {
                print("SomeOtherException：" + e);
            } catch (RuntimeException re) {
                try {
                    throw re.getCause();
                } catch (FileNotFoundException e) {
                    print("FileNotFoundException：" + e);
                } catch (IOException e) {
                    print("IOException：" + e);
                } catch (Throwable e) {
                   print("Throwable：" + e);
                }
            }
        }
    }
}
