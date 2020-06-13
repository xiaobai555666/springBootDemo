package com.ygn.yby.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: yby
 * @description:
 * @author: yby
 * @create: 2020-06-09 14:00
 **/
@Data
public class User implements Serializable {

    private String userId;

    private String name;
}
