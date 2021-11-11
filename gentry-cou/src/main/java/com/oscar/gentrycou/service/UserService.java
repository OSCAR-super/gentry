package com.oscar.gentrycou.service;

import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void insertUser(UserEntity userEntity);

    UserEntity findUserByOpenId(String openId);

    List<UserRoleEntity> findUserRolesByOpenId(String openId);

    void setInitRole(UserRoleEntity user);
}
