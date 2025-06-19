package com.miniBOM.dao;

import com.miniBOM.pojo.User;
import com.miniBOM.utils.IdmeUserClient;
import org.springframework.stereotype.Component;

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

    //更新用户信息
    public void update(User user) {
        IdmeUserClient.update(user);
    }
}
