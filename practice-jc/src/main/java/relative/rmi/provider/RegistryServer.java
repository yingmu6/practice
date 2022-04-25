package relative.rmi.provider;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * @author chensy
 * @date 2022/4/25
 */
public class RegistryServer {
    public static void main(String[] args) throws InterruptedException {
        try {
            LocateRegistry.createRegistry(8000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }
}
