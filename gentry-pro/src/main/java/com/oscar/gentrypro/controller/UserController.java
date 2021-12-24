package com.oscar.gentrypro.controller;

import com.oscar.gentryentity.dto.CrawlDTO;
import com.oscar.gentryentity.dto.UserSearchDTO;
import com.oscar.gentryentity.dto.UserSignDTO;
import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import com.oscar.gentryentity.entity.UserSearchEntity;
import com.oscar.gentrypro.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "findUserByOpenId")
    public UserEntity findUserByOpenId(@RequestBody String openId) {
        UserEntity userEntity =userService.findUserByOpenId(openId);
        return userEntity;
    }

    @RequestMapping(value = "findUserRolesByOpenId")
    public List<UserRoleEntity> findUserRolesByOpenId(@RequestBody String openId) {
        List<UserRoleEntity> userRoleEntities =userService.findUserRolesByOpenId(openId);
        return userRoleEntities;
    }

    @RequestMapping(value = "setInitRole")
    public void setInitRole(@RequestBody UserRoleEntity userRoleEntity) {
        userService.setInitRole(userRoleEntity);
    }

    @RequestMapping(value = "insertUser")
    public void insertUser(@RequestBody UserEntity userEntity) {
        userService.insertUser(userEntity);
    }

    @RequestMapping(value = "setSign")
    public String setSign(@RequestBody UserSignDTO userSignDTO) {
        return String.valueOf(userService.setSign(userSignDTO));
    }

    @RequestMapping(value = "searchUrl")
    public List<CrawlDTO> searchUrl(@RequestBody String searchWords) {
        return userService.searchUrl(searchWords);
    }

    @RequestMapping(value = "addSearchWords")
    public void addSearchWords(@RequestBody UserSearchDTO userSearchDTO) {
        userService.addSearchWords(userSearchDTO);
    }

    @RequestMapping(value = "getSignUrl")
    public List<UserSignDTO> getSignUrl(@RequestBody String account) {
        return userService.getSignUrl(account);
    }

    @RequestMapping(value = "history")
    public List<UserSearchDTO> history(@RequestBody String account) {
        return userService.history(account);
    }

    @RequestMapping(value = "recommendUrl")
    public List<CrawlDTO> recommendUrl() {
        return userService.recommendUrl();
    }
}
