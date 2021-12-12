package com.oscar.gentrycou.filter;

import com.alibaba.fastjson.JSONObject;
import com.oscar.gentrycou.security.config.WxLoginAuthenticationToken;
import com.oscar.gentrycou.security.service.MyWxUserDetailServiceImpl;
import com.oscar.gentrycou.utils.JwtTokenUtils;
import com.oscar.gentrycou.utils.RestResult;
import com.oscar.gentryentity.entity.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
@Slf4j
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private MyWxUserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
            System.out.println("123"+token);
            if (token != null && token.length() > 0) {

                String account = jwtTokenUtils.getAuthAccountFromToken(token);
                String type = jwtTokenUtils.getAuthTypeFromToken(token);
                if (account != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    MyUserDetails userDetails = new MyUserDetails();
                    if ("user".equals(type)) {
                        userDetails = (MyUserDetails) userDetailService.loadUserByUsername(account);
                    }
                    //判断token是否有效 包括 新旧token redis黑名单
                    if (jwtTokenUtils.validateToken(token, userDetails)) {
                        //给使用该JWT令牌的用户进行授权

                        log.info("account: {} 已登入 tpye: {} ", account, type);
                        if ("user".equals(type)) {
                            WxLoginAuthenticationToken authenticationToken =
                                    new WxLoginAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            //设置用户身份授权
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader("Content-type", "application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            RestResult result = new RestResult(0, e.getMessage(), null);
            writer.write(JSONObject.toJSONString(result));
            writer.flush();
            writer.close();
        }
    }
}
