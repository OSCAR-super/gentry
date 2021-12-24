package com.oscar.gentrycou.service;

import com.oscar.gentryentity.dto.CrawlDTO;
import com.oscar.gentryentity.dto.UserSearchDTO;
import com.oscar.gentryentity.dto.UserSignDTO;
import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import com.oscar.gentryentity.entity.UserSearchEntity;
import com.oscar.gentryentity.req.SearchWordReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserAPI {

    void insertUser(UserEntity userEntity);

    UserEntity findUserByOpenId(String openId);

    List<UserRoleEntity> findUserRolesByOpenId(String openId);

    void setInitRole(UserRoleEntity user);

    String setSign(UserSignDTO userSignDTO);

    List<CrawlDTO> searchUrl(SearchWordReq searchWordReq);

    void addSearchWords(UserSearchDTO userSearchDTO);

    List<UserSignDTO> getSignUrl(String account);

    List<UserSearchDTO> history(String account);

    List<CrawlDTO> recommendUrl();
}
