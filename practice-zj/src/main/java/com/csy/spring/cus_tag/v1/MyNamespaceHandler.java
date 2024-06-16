package com.csy.spring.cus_tag.v1;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
/**
 * @author chensy
 * @date 2023/5/23
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
    }

}
