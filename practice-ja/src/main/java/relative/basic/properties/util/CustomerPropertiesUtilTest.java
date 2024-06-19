package relative.basic.properties.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 自定义属性文件 工具测试
 * 功能：保留原来属性文件的注释以及顺序
 *
 * @Author chenSy
 * @Date 2022/12/07 16:55
 * @Description
 */
@Slf4j
public class CustomerPropertiesUtilTest {
//    /**
//     * load配置文件
//     */
//    public static OrderedProperties loadProperties(String fileName) {
//        OrderedProperties properties = new OrderedProperties();
//        FileInputStream is = null;
//
//        try {
//            is = new FileInputStream(fileName);
//            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
//            properties.load(bf);
//        } catch (Exception e) {
//            log.error e.getMessage(), e);
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//
//        return properties;
//    }
//
//    /**
//     * 更新配置文件
//     */
//    public static void updateProperties(String fileName, String key, String value) {
//        OrderedProperties properties = loadProperties(fileName);
//        FileOutputStream os = null;
//        try {
//            os = new FileOutputStream(fileName);
//            properties.setProperty(key, value)
//            properties.store(os);
//        } catch (Exception e) {
//            log.error e.getMessage(), e);
//        } finally {
//            try {
//                os.close();
//            } catch (IOException e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//    }

}
