package com.oscar.gentrypro.controller;

import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentrypro.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "test")
    public String test() {
        UserEntity userEntity=userService.findUserByOpenId("123");
        return userEntity.getOpenId();
    }
}
