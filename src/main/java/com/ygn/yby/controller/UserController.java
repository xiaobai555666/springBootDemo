package com.ygn.yby.controller;

import com.ygn.yby.common.response.R;
import com.ygn.yby.common.util.RedisUtil;
import com.ygn.yby.dto.common.ResultDTO;
import com.ygn.yby.entity.User;
import com.ygn.yby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: yby
 * @description:
 * @author: yby
 * @create: 2020-06-09 14:02
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResultDTO add(@RequestBody @Valid User user){
        userService.insertUser(user);
        RedisUtil.set("yyy",123);
        Map<String,Object> map = new HashMap<>();
        map.put("abc",123333);
        RedisUtil.hmset("key00",map);
        System.out.println(RedisUtil.get("yyy"));
        System.out.println(RedisUtil.hget("key00","abc"));
        return null;
        //return R.ok().put("data",user);
    }
}
