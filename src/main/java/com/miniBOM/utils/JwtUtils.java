//package com.miniBOM.utils;
//
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    @Value("${app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${app.jwtExpirationMs}")
//    private int jwtExpirationMs;
//
//    public String generateToken(Authentication authentication) {
//        String username = authentication.getName();
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException e) {
//            // 无效签名
//        } catch (MalformedJwtException e) {
//            // 无效JWT
//        } catch (ExpiredJwtException e) {
//            // JWT过期
//        } catch (UnsupportedJwtException e) {
//            // 不支持JWT
//        } catch (IllegalArgumentException e) {
//            // JWT为空
//        }
//        return false;
//    }
//}