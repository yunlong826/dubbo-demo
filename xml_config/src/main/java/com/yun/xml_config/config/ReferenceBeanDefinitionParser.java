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
 * @date 2022/5/23 20:10
 */
public class ReferenceBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;
    public ReferenceBeanDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(beanClass);
        genericBeanDefinition.setLazyInit(false);
        genericBeanDefinition.getPropertyValues().add("interface_",element.getAttribute("interface_"));
        genericBeanDefinition.getPropertyValues().add("id",element.getAttribute("id"));
        parserContext.getRegistry().registerBeanDefinition(beanClass.getName(),genericBeanDefinition);
        return null;
    }
}
