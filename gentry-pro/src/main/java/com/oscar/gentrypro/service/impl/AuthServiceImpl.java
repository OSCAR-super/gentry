package com.oscar.gentrypro.service.impl;

import com.oscar.gentrycou.utils.RestResult;
import com.oscar.gentrypro.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Override
    public RestResult userWxLogin(String code) {
        return null;
    }
}
