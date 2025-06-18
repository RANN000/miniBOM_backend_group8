package com.miniBOM.service.impl;

import com.miniBOM.utils.IdmeUserClient;
//import com.miniBOM.utils.JwtUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl {
//    //待启用
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtils jwtUtils;
//    private final IdmeUserClient idmeUserClient;
//
//    public AuthServiceImpl(AuthenticationManager authenticationManager,
//                           JwtUtils jwtUtils,
//                           IdmeUserClient idmeUserClient) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtils = jwtUtils;
//        this.idmeUserClient = idmeUserClient;
//    }

//    // 用户注册
//    public boolean registerUser(String username, String email, String telephone, String password) {
//        // 验证用户名格式
//        if (!isValidUsername(username)) {
//            throw new IllegalArgumentException("用户名格式无效（6-32位字母/数字组合）");
//        }
//
//        // 验证密码强度
//        if (!isValidPassword(password)) {
//            throw new IllegalArgumentException("密码必须包含字母、数字和特殊字符，长度8-32位");
//        }
//
//        return idmeUserClient.createUser(username, email, telephone, password);
//    }
//
//    // 用户登录
//    public Map<String, Object> loginUser(String username, String password) {
//        // 1. 验证凭证
//        boolean isValid = idmeUserClient.verifyCredentials(username, password);
//        if (!isValid) {
//            throw new IllegalArgumentException("用户名或密码错误");
//        }
//
//        // 2. 创建认证令牌
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // 3. 生成JWT
//        String jwt = jwtUtils.generateToken(authentication);
//
//        // 4. 获取用户信息
//        Map<String, Object> userInfo = idmeUserClient.getUserInfo(username);
//
//        // 5. 返回响应
//        Map<String, Object> response = new HashMap<>();
//        response.put("token", jwt);
//        response.put("username", username);
//        response.put("message", "登录成功");
//
//        return response;
//    }
//
//    // 用户名验证
//    private boolean isValidUsername(String username) {
//        return username != null && username.matches("^[a-zA-Z0-9]{6,32}$");
//    }
//
//    // 密码强度验证
//    private boolean isValidPassword(String password) {
//        return password != null && password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+]).{8,32}$");
//    }
}
