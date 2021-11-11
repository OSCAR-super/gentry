package com.oscar.gentrycou.controller;

import com.oscar.gentrycou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/user")
//@PreAuthorize("hasAuthority('user')")
//@RestController
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping(value = "getConsumer")
//    public String getConsumer(){
//        //String str =  userService.getUser();
//        return "str";
//    }
//
//}
