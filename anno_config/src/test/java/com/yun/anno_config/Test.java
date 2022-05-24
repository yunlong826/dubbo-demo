package com.yun.anno_config;

import com.yun.anno_config.pojo.User;
import com.yun.anno_config.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/24 19:45
 */
@SpringBootTest
public class Test {
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @org.junit.jupiter.api.Test
    public void Test(){
        User user = userService.findUser(1);
        System.out.println("user:"+user); //user:User{id=1, name='小红'}
    }
}
