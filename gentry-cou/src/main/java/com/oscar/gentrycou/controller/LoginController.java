package com.oscar.gentrycou.controller;

import com.alibaba.fastjson.JSONObject;
import com.oscar.gentrycou.service.AuthService;
import com.oscar.gentrycou.utils.AuthUtils;
import com.oscar.gentrycou.utils.JwtTokenUtils;
import com.oscar.gentrycou.utils.RestResult;
import com.oscar.gentrycou.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AuthUtils authUtils;

    @GetMapping("/test")
    public String test(String test) {
        return authService.test(test);
    }
    /*
    wx小程序登入接口
     */

//    @PostMapping("/userWxLogin")
//    public RestResult wxLogin(@RequestBody String json) {
//        String code;
//        try {
//            code = (String) JSONObject.parseObject(json).get("code");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtils.error("数据格式错误");
//        }
//
//        return authService.userWxLogin(code);
//    }

    /*
    获取token 信息示例
     */
    @GetMapping("/testToken")
    public void getTokenTest(HttpServletRequest request) {
        //不同权限方法下 获取的是openId 或者管理员用户名
        String account = jwtTokenUtils.getAuthAccountFromRequest(request);
        log.info("当前登入用户为:{}", account);
        String test_account = authUtils.getContextUserDetails().getUsername();
        log.info("当前登入用户为:{}", test_account);
    }
}
