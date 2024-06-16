package com.csy.spring.cus_tag;

import com.csy.spring.cus_tag.v2.MongodbTag;
import com.csy.spring.cus_tag.v2.RedisExt;
import com.csy.spring.cus_tag.v2.RedisTag;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * spring自定义标签测试
 * @author chensy
 * @date 2023/5/19
 */
public class CustomerTagTest {

    /**
     * 自定义标签_概述
     * 1）Since version 2.0, Spring has featured a mechanism for schema-based extensions to the basic Spring XML format for defining and configuring beans.
     * This section is devoted to detailing how you would go about writing your own custom XML bean definition parsers and integrating such parsers into the Spring IoC container.
     *
     * 2）Creating new XML configuration extensions can be done by following these (relatively) simple steps:（创造xml扩展的步骤）
     * a）Authoring（创建） an XML schema to describe your custom element(s). //创建XML的schema来描述自定义元素
     * b）Coding a custom NamespaceHandler implementation (this is an easy step, don’t worry). //编写NamespaceHandler实现类
     * c）Coding one or more BeanDefinitionParser implementations (this is where the real work is done). //编写一个或多个BeanDefinitionParser实现类
     * d）Registering the above artifacts with Spring (this too is an easy step). //用Spring注册上面的构件
     *
     * 3）In addition to the schema（除了模式以外）, we need a NamespaceHandler that will parse all elements of this specific namespace Spring encounters while parsing configuration files.
     * The NamespaceHandler should in our case take care of the parsing of the xxx:yyy element.
     * （除了模式之外，需要定义NamespaceHandler来解析自定义的元素，当spring遇到自定义的元素时，就会回调NamespaceHandler对应的实现）
     *
     * 4）The NamespaceHandler interface is pretty simple in that it features just three methods:（NamespaceHandler的3个方法）
     * a）init() - allows for initialization of the NamespaceHandler and will be called by Spring before the handler is used
     * b）BeanDefinition parse(Element, ParserContext) - called when Spring encounters a top-level element (not nested inside a bean definition or a different namespace). This method can register bean definitions itself and/or return a bean definition.
     * c）BeanDefinitionHolder decorate(Node, BeanDefinitionHolder, ParserContext) - called when Spring encounters an attribute or nested element of a different namespace.
     *
     * 参考链接：
     * 1）https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/xml-custom.html 官网说明
     * 2）https://zhuanlan.zhihu.com/p/107837020 自定义标签实现
     * 3）https://zhuanlan.zhihu.com/p/189896257 spring的BeanDefinition介绍
     */

    /**
     * 场景1：基本使用自定义标签
     */
    @Test
    public void test_basicUse() {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/custom/v2/applicationContext.xml");
        RedisTag redisTag = context.getBean(RedisTag.class);
        System.out.println("redis ip值=" + redisTag.getIp() + ", port值=" + redisTag.getPort() + ", desc值=" + redisTag.getDesc());

        MongodbTag mongodbTag = context.getBean(MongodbTag.class);
        System.out.println("mongodb ip值=" + mongodbTag.getIp() + ", port值=" + mongodbTag.getPort() + ", desc值=" + mongodbTag.getDesc());
    }

    /**
     * 场景2：自定义标签的bean作为其他bean的属性
     */
    @Test
    public void test_inner_bean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/custom/v2/applicationContext.xml");
        RedisExt ext = context.getBean(RedisExt.class);
        System.out.println("redis ip值=" + ext.getRedisTag().getIp() + ", port值=" + ext.getRedisTag().getPort() + ", desc值=" + ext.getRedisTag().getDesc());

        RedisExt ext2 = context.getBean(RedisExt.class);
        System.out.println("ext:" + ext + ", ext2:" + ext2);
    }
}