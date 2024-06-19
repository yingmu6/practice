package relative.basic.system;

/**
 * 系统环境变量获取
 * @Author chenSy
 * @Date 2022/12/07 12:10
 * @Description
 */
public class SystemTest {

    public static void main(String[] args) {
        System.out.println("当前系统属性值：" + System.getProperties());

        System.out.println("当前系统环境变量值：" +System.getenv());

        System.out.println("获取tomcat的目录：" + System.getenv("CATALINA_HOME"));
    }

    /**
     * 问题点：
     * 1）catalina.home是运行时，需要执行的JVM参数吗？
     *
     */
}
