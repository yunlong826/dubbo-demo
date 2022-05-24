package com.yun.anno_config.dao;

import com.yun.anno_config.pojo.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/24 19:38
 */
@Service
public class Data implements CommandLineRunner {
    public Map<Integer, User> users = new HashMap<>();
    @Override
    public void run(String... args) throws Exception {
        users.put(1, new User(1, "小红"));
        users.put(2, new User(2, "小明"));
        users.put(3, new User(3, "小三"));
        System.out.println("初始化数据:"+users);
    }
    public User getUsers(Integer id) {
        return users.get(id);
    }
}
