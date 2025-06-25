package com.miniBOM.service.impl;

//import com.miniBOM.utils.JwtUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import com.miniBOM.dao.UserDao;
import com.miniBOM.pojo.Result;
import com.miniBOM.pojo.User;
import com.miniBOM.service.UserService;
import com.miniBOM.utils.JwtUtil;
import com.miniBOM.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String name) {
        return userDao.getUserByUserName(name);
    }

    // 用户注册
    @Override
    public Result registerUser(String username,
                                String password,
                                String phoneNumber,
                                String email) {

        //密码加密
        String psw = Md5Util.getMD5String(password);

        return userDao.insertUser(username, psw, phoneNumber, email);
    }

    // 用户登录
    @Override
    public Result<String> loginUser(String username, String password) {
        //验证用户名
        User user = userDao.getUserByUserName(username);
        if (user == null) {
            return Result.error("用户不存在");
        }
        //验证密码
        String psw = Md5Util.getMD5String(password);
        if (user.getPassword().equals(psw)) {
            //登录成功
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("id", user.getId());
            claims.put("username", user.getName());
            String token = JwtUtil.genToken(claims);

            return Result.success("SUCCESS",token);
        }

        return Result.error("密码错误");
    }

    @Override
    public Result update(User user) {
        return userDao.update(user);
    }

    @Override
    public Result updatePwd(String name, String old_pwd, String new_pwd) {
        User user = userDao.getUserByUserName(name);
        String psw1 = Md5Util.getMD5String(old_pwd);
        if(!user.getPassword().equals(psw1)) {
            return Result.error("原密码输入错误");
        }
        if(!new_pwd.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])\\S{8,32}$")){
            return Result.error("新密码输入格式错误");
        }

        String psw2 = Md5Util.getMD5String(new_pwd);
        user.setPassword(psw2);
        return userDao.updatePwd(user);
    }
}
