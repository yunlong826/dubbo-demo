package com.yun.xml_config.handler;

import com.yun.xml_config.config.*;
import com.yun.xml_config.xmlPojo.*;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 20:13
 */
public class YunNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("protocol",new ProtocolBeanDefinitionParser(Protocol.class));
        registerBeanDefinitionParser("reference",new ReferenceBeanDefinitionParser(Reference.class));
        registerBeanDefinitionParser("registry",new RegisterBeanDefinitionParser(Registry.class));
        registerBeanDefinitionParser("service",new ServiceBeanDefinitionParser(Service.class));
        registerBeanDefinitionParser("yunDubbo",new YunDubboBeanDefinitionParser(YunDubbo.class));
    }
}
