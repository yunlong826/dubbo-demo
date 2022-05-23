package com.yun.xml_config.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 20:09
 */
public class ServiceBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;
    public ServiceBeanDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(beanClass);
        genericBeanDefinition.setLazyInit(false);
        genericBeanDefinition.getPropertyValues().add("interface_",element.getAttribute("interface_"));
        genericBeanDefinition.getPropertyValues().add("ref",element.getAttribute("ref"));
        parserContext.getRegistry().registerBeanDefinition(beanClass.getName(),genericBeanDefinition);
        return null;
    }
}
