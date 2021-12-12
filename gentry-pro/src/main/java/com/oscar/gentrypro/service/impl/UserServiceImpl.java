package com.oscar.gentrypro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.oscar.gentryentity.dto.CrawlDTO;
import com.oscar.gentryentity.dto.UserSearchDTO;
import com.oscar.gentryentity.dto.UserSignDTO;
import com.oscar.gentryentity.entity.*;
import com.oscar.gentrypro.mapper.*;
import com.oscar.gentrypro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserSignMapper userSignMapper;
    @Resource
    private CrawlMapper crawlMapper;
    @Resource
    private UserSearchMapper userSearchMapper;

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

    @Override
    public int setSign(UserSignDTO userSignDTO) {
        UserSignEntity userSignEntity =new UserSignEntity();
        BeanUtils.copyProperties(userSignDTO,userSignEntity);
        return userSignMapper.insert(userSignEntity);
    }

    @Override
    public List<CrawlDTO> searchUrl(String searchWords) {
        QueryWrapper<CrawlEntity> wrapper = new QueryWrapper<>();
        wrapper.like("proname",searchWords);
        List<CrawlEntity>crawlEntities = crawlMapper.selectList(wrapper);
        List<CrawlDTO>crawlDTOS = new ArrayList<>();
        for (CrawlEntity crawlEntity:crawlEntities) {
            CrawlDTO crawlDTO =new CrawlDTO();
            BeanUtils.copyProperties(crawlEntity,crawlDTO);
            crawlDTOS.add(crawlDTO);
        }
        return crawlDTOS;
    }

    @Override
    public void addSearchWords(UserSearchDTO userSearchDTO) {
        UserSearchEntity userSearchEntity = new UserSearchEntity();
        BeanUtils.copyProperties(userSearchDTO,userSearchEntity);
        userSearchMapper.insert(userSearchEntity);
    }

    @Override
    public List<UserSignDTO> getSignUrl(String account) {
        QueryWrapper<UserSignEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",account);
        List<UserSignEntity>userSignEntities=userSignMapper.selectList(wrapper);
        List<UserSignDTO> userSignDTOS = new ArrayList<>();
        for (UserSignEntity userSignEntity:userSignEntities) {
            UserSignDTO userSignDTO = new UserSignDTO();
            BeanUtils.copyProperties(userSignEntity,userSignDTO);
            userSignDTOS.add(userSignDTO);
        }
        return userSignDTOS;
    }

    @Override
    public List<UserSearchDTO> recommendUrl(String account) {
        QueryWrapper<UserSearchEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",account);
        wrapper.last("ORDER BY RAND() limit 10");
        List<UserSearchEntity>userSearchEntities=userSearchMapper.selectList(wrapper);
        List<UserSearchDTO> userSearchDTOS = new ArrayList<>();
        for (UserSearchEntity userSearchEntity:userSearchEntities) {
            UserSearchDTO userSearchDTO = new UserSearchDTO();
            BeanUtils.copyProperties(userSearchEntity,userSearchDTO);
            userSearchDTOS.add(userSearchDTO);
        }
        return userSearchDTOS;
    }
}
