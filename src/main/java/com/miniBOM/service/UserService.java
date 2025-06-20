package com.miniBOM.service;

import com.miniBOM.pojo.Result;
import com.miniBOM.pojo.User;
import jakarta.validation.constraints.Pattern;

import java.util.Map;

public interface UserService {

    User findByUsername(String name);

    Result registerUser(String username, String email, String telephone, String password);

    Result<String> loginUser(String username, String password);

    Result update(User user);

    Result updatePwd(String name,String old_pwd,String new_pwd);
}