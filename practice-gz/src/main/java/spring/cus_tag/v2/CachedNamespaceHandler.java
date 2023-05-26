package spring.cus_tag.v2;

/**
 * @author chensy
 * @date 2023/5/25
 */
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CachedNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        this.registerBeanDefinitionParser("redis",new CachedBeanDefinitionParser());
        this.registerBeanDefinitionParser("mongodb",new CachedBeanDefinitionParser());
    }
}