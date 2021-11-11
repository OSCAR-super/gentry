package com.oscar.gentrycou.security.service;

import com.oscar.gentrycou.service.UserService;
import com.oscar.gentrycou.utils.WxUtils;
import com.oscar.gentryentity.entity.MyUserDetails;
import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyWxUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private WxUtils wxUtils;

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        UserEntity user = userService.findUserByOpenId(openId);


        if (user==null){
            userService.insertUser(new UserEntity("2",openId,0));
            userService.setInitRole(new UserRoleEntity("2",openId,"user",0));
        }

        List<UserRoleEntity> userRoleEntities = userService.findUserRolesByOpenId(openId);

        List<GrantedAuthority> authoritys = new ArrayList<>();

        for (UserRoleEntity userRoleEntity:userRoleEntities){
            authoritys.add(new SimpleGrantedAuthority(userRoleEntity.getRole()));
        }

        return new MyUserDetails(openId,"vxLogin",authoritys);

    }
}
