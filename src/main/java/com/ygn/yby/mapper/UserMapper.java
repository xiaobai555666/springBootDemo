package com.ygn.yby.mapper;

import com.ygn.yby.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    void insertUser(User user);
}
