package com.miniBOM.controller;

import com.miniBOM.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    private final AuthServiceImpl authService;
//
//    public AuthController(AuthServiceImpl authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(
//            @RequestParam String username,
//            @RequestParam String password,
//            @RequestParam String telephone,
//            @RequestParam String email) {
//
//        boolean result = authService.registerUser(username, email,telephone,password);
//
//        if (result) {
//            return ResponseEntity.ok(Map.of(
//                    "success", true,
//                    "message", "用户注册成功"
//            ));
//        }
//        return ResponseEntity.badRequest().body(Map.of(
//                "success", false,
//                "message", "用户注册失败"
//        ));
//    }
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
