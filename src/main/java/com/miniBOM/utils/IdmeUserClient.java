package com.miniBOM.utils;

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