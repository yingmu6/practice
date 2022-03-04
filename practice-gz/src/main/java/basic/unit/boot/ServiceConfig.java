package basic.unit.boot;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chensy
 * @date 2022/3/4
 */
@RunWith(SpringRunner.class)
@ImportResource(locations = {"classpath:bean.xml"})
public class ServiceConfig {
}
