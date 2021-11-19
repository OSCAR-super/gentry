package com.oscar.gentrypro.controller;

import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import com.oscar.gentrypro.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "findUserByOpenId")
    public UserEntity findUserByOpenId(String openId) {
        UserEntity userEntity =userService.findUserByOpenId(openId);
        return userEntity;
    }
    @RequestMapping(value = "findUserRolesByOpenId")
    public List<UserRoleEntity> findUserRolesByOpenId(String openId) {
        List<UserRoleEntity> userRoleEntities =userService.findUserRolesByOpenId(openId);
        return userRoleEntities;
    }
    @RequestMapping(value = "setInitRole")
    public void setInitRole(UserRoleEntity userRoleEntity) {
        userService.setInitRole(userRoleEntity);
    }
    @RequestMapping(value = "insertUser")
    public void insertUser(UserEntity userEntity) {
        userService.insertUser(userEntity);
    }
}
