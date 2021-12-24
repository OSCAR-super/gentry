package com.oscar.gentrypro.service;

import com.oscar.gentryentity.dto.CrawlDTO;
import com.oscar.gentryentity.dto.UserSearchDTO;
import com.oscar.gentryentity.dto.UserSignDTO;
import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import com.oscar.gentryentity.entity.UserSearchEntity;

import java.util.List;

public interface UserService {
    UserEntity findUserByOpenId(String openId);

    void insertUser(UserEntity userEntity);

    List<UserRoleEntity> findUserRolesByOpenId(String openId);

    void setInitRole(UserRoleEntity user);

    int setSign(UserSignDTO userSignDTO);

    List<CrawlDTO> searchUrl(String searchWords);

    void addSearchWords(UserSearchDTO userSearchDTO);

    List<UserSignDTO> getSignUrl(String account);

    List<UserSearchDTO> history(String account);

    List<CrawlDTO> recommendUrl();
}
