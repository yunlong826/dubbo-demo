package com.yun.anno_config;

import com.yun.anno_config.anno.CustomerScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@CustomerScan(basePackages = {"com.yun.anno_config"})
public class AnnoConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnoConfigApplication.class, args);
    }

}
