package spring.cus_tag.v2;

/**
 * @author chensy
 * @date 2023/5/25
 */
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class CachedBeanDefinitionParser extends AbstractSingleBeanDefinitionParser { //AbstractSingleBeanDefinitionParser解析器，产生单例的bean

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) { //解析xml中的元素值，并设置到Bean属性中
        // 解析元素的属性值
        String id = element.getAttribute("id");
        String ip = element.getAttribute("ip");
        String port = element.getAttribute("port");
        String desc = element.getAttribute("desc");

        // 将解析的值，设置到bean的属性中
        builder.addPropertyValue("id",id);
        builder.addPropertyValue("ip",ip);
        builder.addPropertyValue("port",Integer.parseInt(port));
        builder.addPropertyValue("desc",desc);
    }

    @Override
    protected Class<?> getBeanClass(Element element) { // 获取元素对应的bean类型
        if (element.getTagName().equals("xqc:redis")) {
            return RedisTag.class;
        } else if (element.getTagName().equals("xqc:mongodb")) {
            return MongodbTag.class;
        } else {
            return null;
        }
    }
}