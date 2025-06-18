package com.miniBOM.dao;

import com.miniBOM.pojo.Result;
import com.miniBOM.pojo.User;
import com.miniBOM.utils.IdmeUserClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {
    //根据用户名查找用户
    public User getUserByUserName(String name) {
        return IdmeUserClient.getUser(name);
    }

    //新增用户
    public boolean insertUser(String name,String password,String phoneNumber,String email){
        return IdmeUserClient.insertUser(name,password,phoneNumber,email);
    }
}
