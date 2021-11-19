package com.oscar.gentrycou.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WxUtils {
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String WX_APPID ="wxa21334fbd16c6991";
    public static final String WX_SECRET = "1479ebf4e24a52bc69c110139edb25f4";
    public static final String WX_GRANT_TYPE = "authorization_code";

    public String getOpenIdByCode(String code) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", WX_APPID);
        data.put("secret", WX_SECRET);
        data.put("js_code", code);
        data.put("grant_type",WX_GRANT_TYPE);


        String response = HttpRequest.get(WX_LOGIN_URL).form(data).body();
        System.out.println("Response was: " + response);
        JSONObject obj= JSON.parseObject(response);//将json字符串转换为json对
        System.out.println(obj.toJSONString().toString());
        String openId = (String) obj.get("openid");
        System.out.println(openId);
        return openId;
    }

}
