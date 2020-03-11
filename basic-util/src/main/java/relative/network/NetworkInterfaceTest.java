package relative.network;

import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * NetworkInterface：此类表示由名称组成的网络接口和分配给此接口的IP地址列表。 用于标识组播组所在的本地接口。 接口通常由诸如“le0”的名称所知。
 * @author : chensy
 * Date : 2020-01-16 23:54
 */
public class NetworkInterfaceTest {
    public static void main(String[] args) throws Exception {
        //use();
        basicUse();
    }

    public static void basicUse() throws Exception {
        Enumeration<NetworkInterface> networkEnum = NetworkInterface.getNetworkInterfaces();
        while (networkEnum.hasMoreElements()) { // utun1、utun0、en3、lo0，ifconfig展示出来的不一定是网络接口
            NetworkInterface network = networkEnum.nextElement();
            String displayName = network.getDisplayName();
            boolean isVirtual = network.isVirtual(); // 是否是虚拟接口
            int mtu = network.getMTU();
            int index = network.getIndex();
            System.out.println("网络接口，" + displayName + "，isVirtual=" + isVirtual + ",mtu=" + mtu + ",index=" + index);
        }
    }

    public static void use() throws Exception {

        /**
         * 报出java.net.UnknownHostException: www.baidu.com: nodename nor servname provided, or not known
         * 解：此处的名字不是域名，而是网络接口名，就是通过ifconfig 查看的接口名，比如ifconfig en0,其中en0即为网络接口名
         */

//        InetAddress ip = InetAddress.getByName("www.baidu.com");
//        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);

        // NetworkInterface networkInterface = NetworkInterface.getByName("www.baidu.com"); 2）获取到null对象
        NetworkInterface networkInterface = NetworkInterface.getByName("en3");
        String displayName = networkInterface.getDisplayName();
        System.out.println(displayName);
    }
}
