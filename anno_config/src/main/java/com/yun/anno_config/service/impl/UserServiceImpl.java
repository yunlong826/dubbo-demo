package com.yun.anno_config.service.impl;

import com.yun.anno_config.anno.MyService;
import com.yun.anno_config.dao.Data;
import com.yun.anno_config.pojo.User;
import com.yun.anno_config.service.UserService;

import javax.annotation.Resource;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/24 19:37
 */
@MyService
public class UserServiceImpl implements UserService {
    @Resource
    private Data data;
    @Override
    public User findUser(Integer id) {
        return data.users.get(id);
    }
}
