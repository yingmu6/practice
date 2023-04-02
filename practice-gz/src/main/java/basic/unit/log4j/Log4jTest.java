package basic.unit.log4j;

import org.junit.Test;
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
     * 1）log4j.rootLogger=DEBUG,stdout,D,E  这个属性的配置为 log4j.rootLogger=日志级别,多个附加的文件名（即log4j.appender后面的名称）
     * 2）property文件中，要以log4j.appender.* 开头作为配置，不然会找不到配置（约定优于配置）
     */
    @Test
    public void basicUseByProperties() {
        logger.info("log4j.properties基本使用");
    }

    /**
     * property文件/场景2：能够加载不同位置的文件
     */

    /**
     * property文件/场景3：能够定制日志文件内容
     */

    /**
     * property文件/场景4：能够打印不同级别的文件
     * 注意点：
     * 1）log4j定义了8个级别的log（除去OFF和ALL，可以说分为6个级别），优先级从高到低依次为：OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL。
     * 2）如果将log level设置在某一个级别上，那么比此级别优先级高的log都能打印出来。例如，如果设置优先级为WARN，那么OFF、FATAL、ERROR、WARN 4个级别的log能正常输出，
     * 而INFO、DEBUG、TRACE、 ALL级别的log则会被忽略。Log4j建议只使用四个级别，优先级从高到低分别是ERROR、WARN、INFO、DEBUG。
     */

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
