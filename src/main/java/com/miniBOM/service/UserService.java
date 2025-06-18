package com.miniBOM.service;

import com.miniBOM.pojo.User;

public interface UserService {

    User findByUsername(String name);

    boolean registerUser(String username, String email, String telephone, String password);


}