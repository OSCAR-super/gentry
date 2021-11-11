package com.oscar.gentrycou.feign;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oscar.gentrycou.utils.RestResult;
import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "pro-server",path ="/pro" )
@Component
public interface AuthFeignService {

    @RequestMapping(value = "userWxLogin")
    RestResult userWxLogin(String code);

    @RequestMapping(value = "test")
    String test(String test);

    @RequestMapping(value = "1")
    void insert(UserRoleEntity user);

    @RequestMapping(value = "2")
    UserEntity selectOne(QueryWrapper<UserEntity> wrapper);

    @RequestMapping(value = "3")
    List<UserRoleEntity> selectList(QueryWrapper<UserRoleEntity> wrapper);

    @RequestMapping(value = "4")
    void insert(UserEntity userEntity);
}
