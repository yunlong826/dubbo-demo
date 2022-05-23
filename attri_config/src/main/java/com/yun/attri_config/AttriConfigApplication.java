package com.yun.attri_config;

import com.yun.attri_config.property.DubboPropertiesReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class AttriConfigApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AttriConfigApplication.class, args);
        DubboPropertiesReader reader = new DubboPropertiesReader("application.properties");
    }

}
