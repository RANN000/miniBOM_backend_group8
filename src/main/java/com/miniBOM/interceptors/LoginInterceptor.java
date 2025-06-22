package com.miniBOM.interceptors;

import com.miniBOM.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        return true;
//        try{
//            Map<String,Object> claims= JwtUtil.parseToken(token);
//            return true;
//        }catch (Exception e){
//            response.setStatus(401);
//            return false;
//        }
    }
}
