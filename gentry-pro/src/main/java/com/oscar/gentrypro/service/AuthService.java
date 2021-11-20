package com.oscar.gentrypro.service;

import com.oscar.gentrycou.utils.RestResult;

public interface AuthService {
    RestResult userWxLogin(String code);
}
