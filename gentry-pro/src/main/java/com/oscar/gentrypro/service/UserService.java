package com.oscar.gentrypro.service;

import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;

import java.util.List;

public interface UserService {
    UserEntity findUserByOpenId(String s);

//    void insertUser(UserEntity userEntity);
//
//    UserEntity findUserByOpenId(String openId);
//
//    List<UserRoleEntity> findUserRolesByOpenId(String openId);
//
//    void setInitRole(UserRoleEntity user);

}
