package com.oscar.gentrycou.controller;

import com.oscar.gentrycou.service.UserAPI;
import com.oscar.gentrycou.utils.AuthUtils;
import com.oscar.gentrycou.utils.JwtTokenUtils;
import com.oscar.gentrycou.utils.RestResult;
import com.oscar.gentrycou.utils.ResultUtils;
import com.oscar.gentryentity.dto.CrawlDTO;
import com.oscar.gentryentity.dto.UserSearchDTO;
import com.oscar.gentryentity.dto.UserSignDTO;
import com.oscar.gentryentity.dto.UserSignShowDTO;
import com.oscar.gentryentity.entity.UserSearchEntity;
import com.oscar.gentryentity.req.SearchWordReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/user")
@PreAuthorize("hasAuthority('user')")
@RestController
public class UserController {
    @Autowired
    private UserAPI userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AuthUtils authUtils;

    @PostMapping(value = "/sign")
    public RestResult sign(){
        String account = authUtils.getContextUserDetails().getUsername();
        UserSignDTO userSignDTO =new UserSignDTO(String.valueOf(UUID.randomUUID()),account,new Date(),0);
        return ResultUtils.success(userService.setSign(userSignDTO));
    }

    @PostMapping(value = "/searchUrl")
    public RestResult searchUrl(@RequestBody SearchWordReq searchWordReq){
        String account = authUtils.getContextUserDetails().getUsername();
        List<CrawlDTO>crawlDTOS = userService.searchUrl(searchWordReq);
        UserSearchDTO userSearchDTO = new UserSearchDTO(String.valueOf(UUID.randomUUID()),account,searchWordReq.getSearchWords(),new Date(),0);
        userService.addSearchWords(userSearchDTO);
        return ResultUtils.success(crawlDTOS);
    }

    @PostMapping(value = "/getSignUrl")
    public RestResult getSignUrl(){
        String account = authUtils.getContextUserDetails().getUsername();
        List<UserSignDTO> userSignDTOS = userService.getSignUrl(account);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        List<UserSignShowDTO> userSignShowDTOS =new ArrayList<>();
        for (UserSignDTO userSignDTO : userSignDTOS) {
            UserSignShowDTO userSignShowDTO = new UserSignShowDTO();
            userSignShowDTO.setId(userSignDTO.getId());
            userSignShowDTO.setTime(formatter.format(userSignDTO.getTime()));
            userSignShowDTOS.add(userSignShowDTO);
        }
        return ResultUtils.success(userSignShowDTOS);
    }

    @PostMapping(value = "/history")
    public RestResult history(){
        String account = authUtils.getContextUserDetails().getUsername();
        List<UserSearchDTO> userSearchDTOS = userService.history(account);
        return ResultUtils.success(userSearchDTOS);
    }
}
