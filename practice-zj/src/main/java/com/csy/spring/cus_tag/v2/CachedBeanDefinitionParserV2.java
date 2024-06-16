package com.csy.spring.cus_tag.v2;

/**
 * @author chensy
 * @date 2023/5/25
 */
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class CachedBeanDefinitionParserV2 implements BeanDefinitionParser { //实现BeanDefinitionParser

    private Class<?> beanClass;

    public CachedBeanDefinitionParserV2(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        // 解析元素的属性值
        String id = element.getAttribute("id");
        String ip = element.getAttribute("ip");
        String port = element.getAttribute("port");
        String desc = element.getAttribute("desc");

        // 将解析的值，设置到bean的属性中
        beanDefinition.getPropertyValues().addPropertyValue("id",id);
        beanDefinition.getPropertyValues().addPropertyValue("ip",ip);
        beanDefinition.getPropertyValues().addPropertyValue("port",Integer.parseInt(port));
        beanDefinition.getPropertyValues().addPropertyValue("desc",desc);

        // 获取bean定义注册实例，并将bean的id与RootBeanDefinition进行关联
        BeanDefinitionRegistry beanDefinitionRegistry = parserContext.getRegistry();
        beanDefinitionRegistry.registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }
}