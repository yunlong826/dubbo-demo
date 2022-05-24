package com.yun.anno_config.anno;

import java.lang.annotation.*;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/24 19:33
 */
@Retention(RetentionPolicy.RUNTIME)//注意用这个注解才能在运行时使用反射
@Target({ElementType.TYPE})
@Documented
@CustomerScan(basePackages = "com.yun.anno_config")
public @interface MyService{
    String name() default "";
}
