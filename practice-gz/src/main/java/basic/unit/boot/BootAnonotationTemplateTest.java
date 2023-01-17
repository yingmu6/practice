package basic.unit.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chensy
 * @date 2022/3/4
 * <p>
 * https://www.cnblogs.com/myitnews/p/12330297.html  Spring Boot Test介绍
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServiceConfig.class})
public class BootAnonotationTemplateTest {
    @Autowired
    IHelloService helloService;

    @Test
    public void sayHello() {
        String msg = helloService.sayHello("haha");
        System.out.println(msg);
    }

}
