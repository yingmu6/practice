package relative.basic.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * Properties 继承于 Hashtable。表示一个持久的属性集，属性列表以key-value的形式存在，key和value都是字符串。
 * Properties 类被许多Java类使用。例如，在获取环境变量时它就作为System.getProperties()方法的返回值
 *
 * @author chensy
 * @date 2019-06-04 07:43
 */
public class PropertiesTest {
    public static void main(String[] args) throws Exception { //todo @csy Properties怎么与输入输出流联合使用的
        PropertiesTest test = new PropertiesTest();
        Properties properties = new Properties();
        test.read(properties);

        properties.put("hh", "tt00");
        properties.put("gg", "33388");
        properties.put("gggdd", "232");
        test.write(properties);
        System.out.println(PropertiesTest.class.getResource("")); //带上了前缀 file
        System.out.println(System.getProperty("user.dir"));
        System.out.println(PropertiesTest.class.getResource("").getPath()); //取得当前类所在位置
        System.out.println(PropertiesTest.class.getResource("/").getPath());

        readTomcatProperties();
    }

    /**
     * 将属性文件中的属性值加载到Properties属性集中
     */
    public void read(Properties properties) throws Exception {
        //File file = new File("/Users/chenshengyong/selfPro/tuya_basic_dd/dubbo-demo/dubbo-relative/src/main/java/com/java/relative/basic/properties/test.properties");
//        File file = new File(PropertiesTest.class.getResource("").getPath() + "test.properties");
        //File file = new File("/Users/chenshengyong/selfPro/tuya_basic_dd/dubbo-demo/dubbo-relative/target/classes/com/java/relative/basic/properties/test.properties");

        // 使用绝对路径
//        File file = new File("D:\\self_project\\practice\\practice-jc\\src\\main\\java\\relative\\basic\\properties\\test.properties");

        // 使用相对路径
//        File file = new File("src/main/java/relative/basic/properties/test.properties"); //此处的相对路径，找不到文件

        File file = new File("practice-jc/src/main/java/relative/basic/properties/test.properties"); //带上项目的相对路径，可以使用
        FileInputStream ins = new FileInputStream(file);
        properties.load(ins);
        System.out.println(properties.toString());
    }

    /**
     * 把Properties属性集的内容写到文件中
     */
    public void write(Properties properties) throws Exception {
        properties.setProperty("lang", "en");
        properties.setProperty("address", "china");

        System.out.println(System.getProperty("catalina.home"));

//        FileOutputStream fos = new FileOutputStream("/Users/chenshengyong/selfPro/tuya_basic_dd/dubbo-demo/dubbo-relative/src/main/java/com/java/relative/basic/properties/out.properties");
//        FileOutputStream fos = new FileOutputStream("D:\\self_project\\practice\\practice-jc\\src\\main\\java\\relative\\basic\\properties\\out.properties");
        FileOutputStream fos = new FileOutputStream("practice-jc/src/main/java/relative/basic/properties/out.properties");
        properties.store(fos, "属性输出");
    }

    // 读取tomcat下的属性文件
    public static void readTomcatProperties() throws Exception {
        String filePath =  System.getenv("CATALINA_HOME") + "/webapps/ystone.properties";

        File file = new File(filePath);
        FileInputStream ins = new FileInputStream(file);

        Properties properties = new Properties();
        properties.load(ins);
        System.out.println("tomcat下的属性文件：" + properties.toString());
    }


    /**
     * 问题点：
     * 1）文件怎么使用相对路径？（OK）
     *    解答：可以在idea中右击文件，然后拷贝相对路径即可（选择带上项目名的相对路径）
     *
     * 2）属性文件是否可以返回写的？若没有的key，是否会自动新增
     */

}


