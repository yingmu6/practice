package com.csy.spring.cus_tag.v2;

/**
 * @author chensy
 * @date 2023/5/25
 */
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CachedNamespaceHandler extends NamespaceHandlerSupport {

    public void init() { //spring在解析自定义元素时，回调init方法，可进行初始化操作
        // 第一种解析器：
//        this.registerBeanDefinitionParser("redis",new CachedBeanDefinitionParser()); //将自定义元素与bean解析器关联起来（bean的名称是去掉命名空间的 本地元素名）
//        this.registerBeanDefinitionParser("mongodb",new CachedBeanDefinitionParser());

        // 第二种解析器：
        this.registerBeanDefinitionParser("redis",new CachedBeanDefinitionParserV2(RedisTag.class));
        this.registerBeanDefinitionParser("mongodb",new CachedBeanDefinitionParserV2(MongodbTag.class));
    }
}