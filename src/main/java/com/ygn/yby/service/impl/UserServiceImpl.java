package com.ygn.yby.service.impl;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.ygn.yby.common.annotation.DS;
import com.ygn.yby.common.enums.DBTypeEnum;
import com.ygn.yby.entity.User;
import com.ygn.yby.mapper.UserMapper;
import com.ygn.yby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: yby
 * @description:
 * @author: yby
 * @create: 2020-06-09 14:05
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @DS(DBTypeEnum.db1)
    public void insertUser(User user) {
        System.out.println("user="+user);
        int i = 0;
        int j = 0;
        //System.out.println(i/j);
        userMapper.insertUser(user);
    }
}
