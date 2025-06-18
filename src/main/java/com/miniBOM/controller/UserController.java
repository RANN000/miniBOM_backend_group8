package com.miniBOM.controller;

import com.miniBOM.pojo.Result;
import com.miniBOM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //用户注册
    @PostMapping("/register")
    public Result registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String phoneNumber,
            @RequestParam String email) {

        boolean result = userService.registerUser(username, password, email, phoneNumber);

        if (result) {
            return Result.success();
        } else {
            return Result.error("注册失败");
        }
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(
//            @RequestParam String username,
//            @RequestParam String password) {
//
//        try {
//            Map<String, Object> response = authService.loginUser(username, password);
//            return ResponseEntity.ok(response);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "success", false,
//                    "message", e.getMessage()
//            ));
//        }
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logoutUser() {
//        // 清除安全上下文
//        SecurityContextHolder.clearContext();
//        return ResponseEntity.ok(Map.of(
//                "success", true,
//                "message", "用户已登出"
//        ));
//    }
}
