package spring.cus_tag.v2;

/**
 * @author chensy
 * @date 2023/5/25
 */
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class RedisBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        String ip = element.getAttribute("ip");
        String port = element.getAttribute("port");
        String desc = element.getAttribute("desc");
        builder.addPropertyValue("ip",ip);
        builder.addPropertyValue("port",Integer.parseInt(port));
        builder.addPropertyValue("desc",desc);
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return RedisTag.class;
    }
}