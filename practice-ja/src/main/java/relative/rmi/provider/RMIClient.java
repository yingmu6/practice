package relative.rmi.provider;

import relative.rmi.api.HelloService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author chensy
 * @date 2022/4/25
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);
            HelloService helloService = (HelloService) registry.lookup("hello");
            helloService.sayHello();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
