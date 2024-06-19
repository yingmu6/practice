package relative.rmi.provider;

import relative.rmi.api.HelloService;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author chensy
 * @date 2022/4/25
 */
public class RMIServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        try {
            HelloService stub = (HelloService) UnicastRemoteObject.exportObject(helloService, 4000);
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);
            registry.bind("hello", stub);
        } catch (AlreadyBoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 参考文档：https://segmentfault.com/a/1190000016598069
     * 问题点：先启动RegisterServer后，再运行本类，为什么会报"java.rmi.server.ExportException: remote object implements illegal remote interface;"
     */
}


