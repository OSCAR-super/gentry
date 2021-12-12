package com.oscar.gentrycou.feign;

import com.oscar.gentryentity.dto.CrawlDTO;
import com.oscar.gentryentity.dto.UserSearchDTO;
import com.oscar.gentryentity.dto.UserSignDTO;
import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import com.oscar.gentryentity.entity.UserSearchEntity;
import com.oscar.gentryentity.req.SearchWordReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "pro-server",path ="/pro" )
@Component
public interface UserFeignService {
    @RequestMapping(value = "insertUser")
    void insertUser(UserEntity userEntity);

    @RequestMapping(value = "findUserByOpenId")
    UserEntity findUserByOpenId(String openId);

    @RequestMapping(value = "setInitRole")
    void setInitRole(UserRoleEntity user);

    @RequestMapping(value = "findUserRolesByOpenId")
    List<UserRoleEntity> findUserRolesByOpenId(String openId);

    @RequestMapping(value = "setSign")
    String setSign(UserSignDTO userSignDTO);

    @RequestMapping(value = "searchUrl")
    List<CrawlDTO> searchUrl(String searchWords);

    @RequestMapping(value = "addSearchWords")
    void addSearchWords(UserSearchDTO userSearchDTO);

    @RequestMapping(value = "getSignUrl")
    List<UserSignDTO> getSignUrl(String account);

    @RequestMapping(value = "recommendUrl")
    List<UserSearchDTO> recommendUrl(String account);
}
