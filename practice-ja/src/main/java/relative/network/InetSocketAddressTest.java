package relative.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * IP Socket Address (IP address + port number) 或(hostname + port number)
 * IP的socket地址，包含ip与port对或hostname与port对
 * @author : chensy
 * Date : 2020-02-17 10:13
 */
public class InetSocketAddressTest {
    public static void main(String[] args) throws Exception {
        basicUse();
    }

    public static void basicUse() throws Exception {
        InetSocketAddress socketA1 = new InetSocketAddress(2181);
        InetAddress ip1 = socketA1.getAddress();
        // 输出 account.jetbrains.com；0.0.0.0
        System.out.println("socketA1=" + ip1.getCanonicalHostName() + "；" + ip1.getHostAddress());

        InetAddress ip = InetAddress.getByName("www.baidu.com");
        InetSocketAddress socketA2 = new InetSocketAddress(ip, 80);
        InetAddress ip2 = socketA2.getAddress();
        // 输出 socketA2=180.101.49.11；180.101.49.11
        System.out.println("socketA2=" + ip2.getCanonicalHostName() + "；" + ip2.getHostAddress());

    }

}
/**
 * https://blog.csdn.net/wo541075754/article/details/66971888  Java中InetAddress和InetSocketAddress的区别
 * 在Java中InetAddress和InetSocketAddress看起来很相似，用来描述IP地址和主机名称，但InetSocketAddress包含端口号port
 * 并且InetSocketAddress包含InetAddress
 */


