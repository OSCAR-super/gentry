package com.oscar.gentrycou.service;

import com.oscar.gentrycou.utils.RestResult;

public interface AuthService {

    RestResult userWxLogin(String code);

    String test(String test);
}
