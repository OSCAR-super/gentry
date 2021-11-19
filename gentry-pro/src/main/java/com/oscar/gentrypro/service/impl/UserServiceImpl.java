package com.oscar.gentrypro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import com.oscar.gentrypro.mapper.UserMapper;
import com.oscar.gentrypro.mapper.UserRoleMapper;
import com.oscar.gentrypro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.UUID;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void insertUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
    }

    @Override
    public UserEntity findUserByOpenId(String s) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",s);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<UserRoleEntity> findUserRolesByOpenId(String openId) {
        QueryWrapper<UserRoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openId);
        return userRoleMapper.selectList(wrapper);
    }

    @Override
    public void setInitRole(UserRoleEntity user) {
        userRoleMapper.insert(user);
    }
}
