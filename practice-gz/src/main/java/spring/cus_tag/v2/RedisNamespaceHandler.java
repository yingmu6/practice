package spring.cus_tag.v2;

/**
 * @author chensy
 * @date 2023/5/25
 */
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RedisNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        this.registerBeanDefinitionParser("redis",new RedisBeanDefinitionParser());
    }
}