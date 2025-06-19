package com.miniBOM.controller;

import com.miniBOM.pojo.Result;
import com.miniBOM.pojo.User;
import com.miniBOM.service.UserService;
import com.miniBOM.utils.JwtUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //用户注册
    @PostMapping("/register")
    public Result registerUser(
            @RequestParam @Pattern(regexp = "^(?=.*[a-zA-Z0-9_-])\\S{2,20}$") String username,
            @RequestParam @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])\\S{6,20}$") String password,
            @RequestParam String phoneNumber,
            @RequestParam String email) {

        boolean result = userService.registerUser(username, password, email, phoneNumber);

        if (result) {
            return Result.success();
        } else {
            return Result.error("注册失败");
        }
    }

    //用户登录
    @PostMapping("/login")
    public Result<String> loginUser(
            @RequestParam @Pattern(regexp = "^(?=.*[a-zA-Z0-9_-])\\S{2,20}$") String username,
            @RequestParam @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])\\S{6,20}$") String password) {

        try {
            return userService.loginUser(username, password);
        } catch (Exception e) {
            return Result.error("登录失败");
        }

    }

    //获取用户信息
    @GetMapping("/userInfo")
    public Result<User> infoUser(
            @RequestHeader(name="Authorization")String token) {
        Map<String,Object> map = JwtUtil.parseToken(token);
        String name=(String)map.get("name");
        User user = userService.findByUsername(name);
        return Result.success(user);
    }

    //修改用户信息（电话/邮箱）
    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody User user) {
        if(user.getEmail() != null){
            if(!user.getEmail().matches("^(?i)[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")){
                return Result.error("邮箱格式错误");
            }
        }
        if(user.getPhoneNumber() != null){
            if(!user.getPhoneNumber().matches("^(?=.*[0-9])\\S{2,20}$")){
                return Result.error("手机号格式填写错误");
            }
        }

        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updateUserPwd(
            @RequestHeader(name="Authorization")String token,
            @RequestBody Map<String, String> params) {
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        if(!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)) {
            return Result.error("缺少必要参数");
        }

        Map<String,Object> map = JwtUtil.parseToken(token);
        String name=(String)map.get("name");

        try{
            return userService.updatePwd(name,old_pwd,new_pwd);
        }catch(Exception e){
            return Result.error("修改失败");
        }

    }

}
