package com.miniBOM.controller;

import com.miniBOM.pojo.Result;
import com.miniBOM.pojo.User;
import com.miniBOM.service.UserService;
import com.miniBOM.utils.JwtUtil;

import javax.validation.constraints.Pattern;

import com.miniBOM.utils.ThreadLocalUtil;
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

        try {
            return userService.registerUser(username, password, phoneNumber, email);
        } catch (Exception e) {
            return Result.error(e.getMessage());
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
            return Result.error(e.getMessage());
        }

    }

    //获取用户信息
    @GetMapping("/userInfo")
    public Result<User> infoUser() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = map.get("username").toString();
        try {
            User user = userService.findByUsername(name);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //修改用户信息（电话/邮箱）
    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody Map<String, String> params) {
        User user = new User();
        user.setName(params.get("username"));
        user.setEmail(params.get("email"));
        user.setPhoneNumber(params.get("phoneNumber"));
        if (!user.getEmail().isEmpty()) {
            if (!user.getEmail().matches("^(?i)[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")) {
                return Result.error("邮箱格式错误");
            }
        }
        if (!user.getPhoneNumber().isEmpty()) {
            if (!user.getPhoneNumber().matches("^(?=.*[0-9])\\S{2,20}$")) {
                return Result.error("手机号格式填写错误");
            }
        }

        try {
            return userService.update(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PatchMapping("/updatePwd")
    public Result updateUserPwd(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody Map<String, String> params) {
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        if (old_pwd.isEmpty() || new_pwd.isEmpty() || re_pwd.isEmpty()) {
            return Result.error("缺少必要参数");
        }

        Map<String, Object> map = JwtUtil.parseToken(token);
        String name = (String) map.get("username");

        try {
            return userService.updatePwd(name, old_pwd, new_pwd);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }

}
