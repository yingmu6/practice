package relative.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * 网络IP测试（InetAddress此类表示Internet协议（IP）地址）
 * @author : chensy
 * Date : 2020-01-16 17:11
 */
public class InetAddressTest {

    /**
     * IP_概述
     *
     * 参考链接：
     * a）https://zhuanlan.zhihu.com/p/72988255 127.0.0.1和0.0.0.0地址的区别
     */

    /**
     * 场景1：了解A~E类地址
     */

    public static void main(String[] args) throws Exception {
        //localHost();
        bulid();
        basicUse();
    }

    // 基本使用
    public static void basicUse() throws Exception {
        InetAddress ip = InetAddress.getLocalHost(); //本机的ip地址
        String canonicalHostName = ip.getCanonicalHostName(); // 完全限定域名，如192.168.1.102
        boolean isReachable = ip.isReachable(1000); //在指定毫秒数是否能到达
        byte[] address = ip.getAddress();
        System.out.println(canonicalHostName + "，" + isReachable + ",[0]=" + address[0] + ",[1]=" + address[1]);

    }

    public static void bulid() throws Exception {
        InetAddress ip = InetAddress.getByName("www.baidu.com"); // 返回指定域名的IP地址
        System.out.println(ip.getHostAddress());
    }

    public static void localHost() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            // todo @chenSy 为啥显示乱码 "MacBook-Pro-csy.local,��f"
            String hostName = ip.getHostName(); // 输出"MacBook-Pro-csy.local"
            byte [] ipByte = ip.getAddress();
            // System.out.println(ip.getHostName() + "," + new String(ip.getAddress(), "UTF-8"));
            System.out.println(hostName + "," + new String(ipByte, Charset.forName("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        // 构造函数非public，不能直接实例化，调用公有方法实例化
        //InetAddress ip = new InetAddress();

        try {
            InetAddress ip = InetAddress.getLocalHost();
//            String hostName = ip.getHostName();
//            String hostAddress = ip.getHostAddress();
//            String address = ip.getAddress();
            System.out.println(ip.getHostName() + "," + new String(ip.getAddress()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 怎样才能输出192.168.1.102 这种格式的数据？
     * 解：可以用getHostAddress获取字符串
     */
}
