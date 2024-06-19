package relative.rmi.api;

import java.rmi.Remote;

/**
 * @author chensy
 * @date 2022/4/25
 */
public interface HelloService extends Remote {
    void sayHello();
}