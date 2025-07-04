package com.miniBOM.service;

import com.miniBOM.pojo.Result;
import com.miniBOM.pojo.User;


public interface UserService {

    User findByUsername(String name);

    Result registerUser(String username, String password, String phoneNumber, String email);

    Result<String> loginUser(String username, String password);

    Result update(User user);

    Result updatePwd(String name,String old_pwd,String new_pwd);
}