package com.oscar.gentrycou.service.impl;

import com.oscar.gentrycou.feign.AuthFeignService;
import com.oscar.gentrycou.redis.RedisService;
import com.oscar.gentrycou.security.config.WxLoginAuthenticationToken;
import com.oscar.gentrycou.utils.JwtTokenUtils;
import com.oscar.gentrycou.utils.RestResult;
import com.oscar.gentryentity.entity.MyUserDetails;
import com.oscar.gentrycou.service.AuthAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthAPIImpl implements AuthAPI {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Resource
    private RedisService redisService;

    @Autowired
    private AuthFeignService authFeignService;

    @Override
    public String test(String test) {
        return authFeignService.test(test);
    }

    @Override
    public RestResult userWxLogin(String code) {
        Authentication authentication;
        try {
            // 进行身份验证,
            authentication = authenticationManager.authenticate(
                    new WxLoginAuthenticationToken(code, "vxLogin"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RestResult(0, e.getMessage(), null);
        }

        MyUserDetails loginUser = (MyUserDetails) authentication.getPrincipal();
        RestResult result = new RestResult(1, "登入成功", null);

        log.info("用户:{} 已经登入。。。本次权限为:{}", loginUser.getUsername(), loginUser.getAuthorities().toString());


        //主动失效 设置黑名单 并关闭已存在socket
        if (redisService.userLogoutByServer(loginUser.getUsername()) == 0) {
            return null;
        }
        result.put("token", jwtTokenUtils.generateToken(loginUser, "user"));
        return result;
    }


}
