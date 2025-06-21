package com.miniBOM.dao;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.minibom.delegator.UserDelegator;
import com.huawei.innovation.rdm.minibom.dto.entity.*;
import com.miniBOM.pojo.Result;
import com.miniBOM.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {
    @Autowired
    private UserDelegator userDelegator;

    //根据用户名查找用户
    public User getUserByUserName(String name) {
        UserNameDTO userNameDTO = new UserNameDTO();
        userNameDTO.setName(name);
        UserViewDTO userViewDTO = userDelegator.getByName(userNameDTO);
        User user = new User();
        user.setId(userViewDTO.getId());
        user.setName(userViewDTO.getName());
        user.setEmail(userViewDTO.getEmail());
        user.setPhoneNumber(userViewDTO.getPhoneNumber());
        user.setPassword(userViewDTO.getUserPassword());
        return user;
    }

    //根据id查找用户
    public User getUserByUserId(Long id){
        PersistObjectIdDecryptDTO decryptDTO = new PersistObjectIdDecryptDTO();
        decryptDTO.setId(id);
        UserViewDTO userViewDTO = userDelegator.get(decryptDTO);
        User user = new User();
        user.setId(userViewDTO.getId());
        user.setName(userViewDTO.getName());
        user.setEmail(userViewDTO.getEmail());
        user.setPhoneNumber(userViewDTO.getPhoneNumber());
        user.setPassword(userViewDTO.getUserPassword());
        return user;
    }

    //根据用户名查找id
    public Long getIdByUserName(String name) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("Name", ConditionType.EQUAL, name);
        List<UserQueryViewDTO> userQueryViewDTO = userDelegator.query(queryRequestVo, new RDMPageVO(1, 1));
        UserQueryViewDTO userInfo = userQueryViewDTO.get(0);
        return userInfo.getId();
    }

    //新增用户
    public Result insertUser(String name, String password, String phoneNumber, String email) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setName(name);
        userCreateDTO.setUserPassword(password);
        userCreateDTO.setPhoneNumber(phoneNumber);
        userCreateDTO.setEmail(email);
        UserViewDTO resultVo = userDelegator.create(userCreateDTO);
        return Result.success(resultVo.getId());
    }

    //更新用户信息
    public Result update(User user) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        Long id = getIdByUserName(user.getName());
        userUpdateDTO.setId(id);
        userUpdateDTO.setPhoneNumber(user.getPhoneNumber());
        userUpdateDTO.setEmail(user.getEmail());
        userUpdateDTO.setUserPassword(user.getPassword());
        UserViewDTO userViewDTO = userDelegator.update(userUpdateDTO);
        return Result.success(userViewDTO.getId());
    }



}
