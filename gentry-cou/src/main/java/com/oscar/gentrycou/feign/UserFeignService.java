package com.oscar.gentrycou.feign;

import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
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
}
