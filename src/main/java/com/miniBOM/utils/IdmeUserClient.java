package com.miniBOM.utils;

import com.miniBOM.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
/**
 * 用于调用IDME接口的工具类，暂未开发
* */
@Component
public class IdmeUserClient {

    private final RestTemplate restTemplate = new RestTemplate();

    //根据用户名查找用户
    public static User getUser(String name){
        User user = new User();
        return user;
    }

    //创建新用户
    public static boolean insertUser(String name, String password, String phoneNumber, String email) {
        return true;
    }

    // 创建用户
    public boolean createUser(String name, String email, String telephone, String password) {
        //暂未开发
        return false;
    }

    // 验证用户凭证
    public boolean verifyCredentials(String identifier, String password) {
        //暂未开发
        return false;
    }

    // 获取用户信息
    public Map<String, Object> getUserInfo(String identifier) {
        //暂未开发
        return java.util.Collections.emptyMap();
    }
}