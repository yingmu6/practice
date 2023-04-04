package basic.unit.log4j;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author chenSy
 * @Date 2023/04/02 13:22
 * @Description
 */
public class Log4jTest {

    private static final Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    /**
     * https://logging.apache.org/log4j/2.x/manual/configuration.html#Appenders log4j官方介绍
     * https://docs.oracle.com/cd/E29578_01/webhelp/cas_webcrawler/src/cwcg_config_log4j_file.html log4j.properties配置
     *
     * 注意点：
     * 1）log4j.properties文件改动后，要重新编译下项目，产生字节码文件，不然不生效的（重新打开字节码，即target中的文件中，看下是否生效）
     * 2）log4j.properties文件默认是放在resources目录下的，这个目录可以在main的resources下、也可以在test的resources下
     * (但是应该要分成两个文件，因为有时候项目，是跳过test目录的，所以main中没有properties文件，就会加载不了)
     */

    //-----------log4j.properties文件使用-----------------

    /**
     * property文件/场景1：基本使用，能打印日志
     * 注意点：
     * 1）log4j.rootLogger=DEBUG,stdout,D,E  这个属性的配置为 log4j.rootLogger=日志级别,多个输出的文件位置（即log4j.appender后面的名称，若不指定位置，则不会输出）
     * (级别可以是大小写，如DEBUG也可以是debug)
     * 2）property文件中，要以log4j.appender.* 开头作为配置，不然会找不到配置（约定优于配置）
     * 3）输出的访问计算方式（求交集）：log4j.rootLogger设置的是全局日志级别范围，log4j.appender.xxx.Threshold设置的是文件的日志级别范围
     * 例如：log4j.rootLogger=DEBUG, log4j.appender.xxx.Threshold=INFO, 即为（DEBUG、INFO、WARN、ERROR）与（INFO、WARN、ERROR）求交集
     * 得到交集为（INFO、WARN、ERROR），即交集内的级别日志都能输出到指定的文件
     */
    @Test
    public void test_basicUse() {
        logger.info("The properties file of log4j basic use!");
    }

    /**
     * property文件/场景2：能够加载不同位置的log4j.properties文件、也能将日志文件输出到不同位置
     * 注明：输出日志到不同位置，可参考log4j.properties中的log4j.appender.Test等配置（若把日志输出到文件，则必须以log4j.appender作为前缀）
     * 参考：https://juejin.cn/s/log4j%E6%8C%87%E5%AE%9A%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E4%BD%8D%E7%BD%AE
     */
    @Test
    public void test_multiPropertiesLocation() {
//        String log4jLocation = "META-INF/log4j/log4j-test.properties"; //这个位置找不出来
        String log4jLocation = "src/main/resources/META-INF/log4j/log4j-test.properties"; //这个位置可以找到文件（可以尝试下）
        PropertyConfigurator.configure(log4jLocation);

        logger.info("This is a properties file from different locations.");
    }

    /**
     * property文件/场景3：能够定制日志文件内容（且在指定的位置，找到日志文件）
     */


    /**
     * property文件/场景4：能够打印不同级别的文件
     * 注意点：
     * 1）log4j定义了8个级别的log（除去OFF和ALL，可以说分为6个级别），优先级从高到低依次为：OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL。
     * 2）如果将log level设置在某一个级别上，那么比此级别优先级高的log都能打印出来。例如，如果设置优先级为WARN，那么OFF、FATAL、ERROR、WARN 4个级别的log能正常输出，
     * 而INFO、DEBUG、TRACE、 ALL级别的log则会被忽略。Log4j建议只使用四个级别，优先级从高到低分别是ERROR、WARN、INFO、DEBUG。
     * （经过实践证明：优先级从高到低，的确是：ERROR > WARN > INFO > DEBUG）
     * 3）控制台日志，即org.apache.log4j.ConsoleAppender会输出DEBUG及以上的日志（最终的日志输出：要结合log4j.rootLogger做交集）
     */
    @Test
    public void test_multiLevel() {
        logger.debug("The debug level !"); //代码中打印了日志，不一定会输出来，关键是看log4j.properties配置的日志级别
        logger.info("The info level !");
        logger.warn("Ths warn level !");
        logger.error("The error level !");
    }

    /**
     * property文件/场景5：能够使用注解获取属性值
     * 如：${logger.level:info}
     */

    /**
     * property文件/场景6：确定额外配置的使用场景
     * 如：log4j.logger.org.apache.zookeeper
     * log4j.logger.org.I0Itec.zkclient等
     */

    /**
     * properties文件/场景7：接受-D输入的参数
     * 1）可以编辑配置 Edit Configurations，传入虚拟机参数 如： -Ddynamic.path=/self-project/idea-logs/idea-dynamic-logs
     *
     */
    @Parameterized.Parameter
    @Test
    public void test_acceptDynamicParameter() {
        logger.info("The info level of Dynamic !");
        logger.warn("The warn level of Dynamic !");
        logger.error("The error level of Dynamic !");
    }

    //----------log4j.xml文件使用----------------
    /**
     * xml文件/场景1：基本使用，能打印日志
     */

    /**
     * xml文件/场景2：能够加载不同位置的文件
     */

     /**
     * xml文件/场景3：能够定制日志文件内容
     */

     /**
     * xml文件/场景4：能够打印不同级别的文件
     */
}
