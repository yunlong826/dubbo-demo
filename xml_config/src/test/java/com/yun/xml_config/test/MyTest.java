package com.yun.xml_config.test;

import com.yun.xml_config.xmlPojo.Reference;
import com.yun.xml_config.xmlPojo.Registry;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 20:22
 */
public class MyTest {
    @Test
    public void Test(){
//        ApplicationContext ac = new ClassPathXmlApplicationContext("yun-custom.xml");
//        Registry registry = (Registry) ac.getBean(Registry.class.getName());
//        System.out.println(Registry.class.getName());
//        System.out.println(registry.getAddress());

        ApplicationContext ac = new ClassPathXmlApplicationContext("yun-custom.xml");
        Registry bean = ac.getBean(Registry.class.getName(), Registry.class);
        System.out.println(bean);

    }

}
