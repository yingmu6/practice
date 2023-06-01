package relative.basic.properties;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

 /**
 * @author chensy
 * @date 2019-06-04 07:43
 */
public class PropertiesTest {

     /**
      * Properties概述：
      * a）Properties 继承于 Hashtable。表示一个持久的属性集，属性列表以key-value的形式存在，key和value都是字符串。
      * b）Properties are configuration values managed as key/value pairs.（Properties管理者key/value对）
      * c）the java.util.Properties – a utility class designed for handling this type of configuration files.
      * （Properties是为了处理配置文件而设计的的使用类）
      *
      * 参考链接：
      * a）https://docs.oracle.com/javase/tutorial/essential/environment/properties.html 官网描述
      * b）https://www.baeldung.com/java-properties Properties使用介绍
      */

     /**
      * 场景1：基本使用
      */
    @Test
    public void test_basic() {
        Properties properties = new Properties();
        properties.put("name", "zhang");
        properties.put("age", 11);

        System.out.println(properties);
    }

     /**
      * 场景2：将属性文件中的属性值加载到Properties属性集中
      */
     @Test
    public void read() throws Exception {
         Properties properties = new Properties();
         String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
         String filePath = rootPath + "test.properties"; //todo @csy 为什么此处方式，找不到路径，路径为/Users/shengyong.chen/self_pro/practice/practice-jc/target/classes/test.properties

         String filePath2 = "/Users/shengyong.chen/self_pro/practice/practice-jc/src/main/java/relative/basic/properties/test.properties"; //绝对路径
         File file = new File(filePath2);
         FileInputStream ins = new FileInputStream(file);
         properties.load(ins);
         System.out.println("打印属性值：" + properties);
    }

      /**
     * 场景3：把Properties属性集的内容写到文件中
     */
     @Test
     public void write() throws Exception {
         Properties properties = new Properties();
        properties.setProperty("lang", "en");
        properties.setProperty("address", "china");
         properties.setProperty("city", "hangzhou");
        System.out.println(System.getProperty("catalina.home"));

        String path = "practice-jc/src/main/java/relative/basic/properties/out.properties";
        String path2 = "/Users/shengyong.chen/self_pro/practice/practice-jc/src/main/java/relative/basic/properties/out.properties"; //绝对路径可以
        FileOutputStream fos = new FileOutputStream(path2);
        properties.store(fos, "属性输出");
    }

     /**
      * 场景4：先从属性文件中读取内容，再写到属性文件中（避免属性文件中的内容被覆盖）
      */
     @Test
     public void read_write() throws Exception {

         String path2 = "/Users/shengyong.chen/self_pro/practice/practice-jc/src/main/java/relative/basic/properties/out.properties";
         File file = new File(path2);
         FileInputStream ins = new FileInputStream(file);
         Properties properties = new Properties();
         properties.load(ins);

         properties.setProperty("lang2", "en");
         properties.setProperty("address2", "china");
         properties.setProperty("city2", "hangzhou");
         System.out.println(System.getProperty("catalina.home"));

         FileOutputStream fos = new FileOutputStream(path2);
         properties.store(fos, "属性输出");
     }

    /**
     * 场景5：值覆盖场景
     */
    @Test
    public void test_value() {
        Properties properties = new Properties();
        properties.setProperty("aa", "11");
        properties.setProperty("bb", "22");

        Properties properties2 = new Properties();
        properties2.setProperty("aa", "101");
        properties2.setProperty("cc", "33");


        System.out.println("值覆盖之前：" + properties);
        properties.putAll(properties2);
        System.out.println("值覆盖之后：" + properties);
    }

      /**
      * 场景6：从XML读取属性值以及将属性值写入XML
      */
      @Test
      public void test_properties_by_xml() throws Exception {
          // 从XML中读取属性值
          Properties properties = new Properties();
         String xmlPath = "/Users/shengyong.chen/self_pro/practice/practice-jc/src/main/java/relative/basic/properties/test.xml";
         properties.loadFromXML(new FileInputStream(xmlPath));

         System.out.println("从xml加载的属性值：" + properties);

         // 往xml中写属性值
          properties.put("country", "china");
          properties.storeToXML(new FileOutputStream(xmlPath), "properties by xml !");
      }
 }


