package com.oscar.gentrycou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oscar.gentrycou.feign.AuthFeignService;
import com.oscar.gentrycou.feign.UserFeignService;
import com.oscar.gentrycou.service.UserAPI;
import com.oscar.gentrycou.utils.RestResult;
import com.oscar.gentrycou.utils.ResultUtils;
import com.oscar.gentryentity.dto.CrawlDTO;
import com.oscar.gentryentity.dto.UserSearchDTO;
import com.oscar.gentryentity.dto.UserSignDTO;
import com.oscar.gentryentity.entity.UserEntity;
import com.oscar.gentryentity.entity.UserRoleEntity;
import com.oscar.gentryentity.entity.UserSearchEntity;
import com.oscar.gentryentity.req.SearchWordReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.UUID;
@Service
@Slf4j
public class UserAPIImpl implements UserAPI {
    @Autowired
    private AuthFeignService authFeignService;
    @Autowired
    private UserFeignService userFeignService;
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void insertUser(UserEntity userEntity) {
        userFeignService.insertUser(userEntity);
    }

    @Override
    public UserEntity findUserByOpenId(String openId) {
        return userFeignService.findUserByOpenId(openId);
    }

    @Override
    public List<UserRoleEntity> findUserRolesByOpenId(String openId) {
        return userFeignService.findUserRolesByOpenId(openId);
    }

    public RestResult doneLoad(MultipartFile file) {
        if (file.isEmpty()){
            return ResultUtils.systemError();
        }
        String fileName=null;
        String name=file.getOriginalFilename();
        String[] a = name.split("\\.");
        String type=a[a.length-1];
        RestResult result = null;
        if (type.equals("png")||type.equals("jpg")){
            fileName = String.valueOf(UUID.randomUUID())+"."+type;
            String path="C:\\upload";
            InputStream inputStream = null;
            File files = null;
            try {
                files = File.createTempFile("temp", null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.transferTo(files);   //sourceFile为传入的MultipartFile
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream = new FileInputStream(files);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            files.deleteOnExit();
            OutputStream os = null;
            try {
                byte[] bs = new byte[1024];
                int len=1024;
                File tempFile = new File(path);
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                }
                os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
                while (true) {
                    len = inputStream.read(bs) ;
                    if (len==-1){
                        break;
                    }
                    os.write(bs, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    os.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            result = ResultUtils.success(fileName);
        }
        return result;
    }

    @Override
    public void setInitRole(UserRoleEntity user) {
        userFeignService.setInitRole(user);
    }

    @Override
    public String setSign(UserSignDTO userSignDTO) {
        return userFeignService.setSign(userSignDTO);
    }

    @Override
    public List<CrawlDTO> searchUrl(SearchWordReq searchWordReq) {
        return userFeignService.searchUrl(searchWordReq.getSearchWords());
    }

    @Override
    public void addSearchWords(UserSearchDTO userSearchDTO) {
        userFeignService.addSearchWords(userSearchDTO);
    }

    @Override
    public List<UserSignDTO> getSignUrl(String account) {
        return userFeignService.getSignUrl(account);
    }

    @Override
    public List<UserSearchDTO> history(String account) {
        return userFeignService.history(account);
    }

    @Override
    public List<CrawlDTO> recommendUrl() {
        return userFeignService.recommendUrl();
    }

}
