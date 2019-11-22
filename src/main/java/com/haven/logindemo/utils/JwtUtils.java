package com.haven.logindemo.utils;

import com.haven.logindemo.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author HavenTong
 * @date 2019/11/22 1:46 下午
 */
@Component
@Slf4j
public class JwtUtils {
    private static final String key = "ECNUHAVEN";
    private static final long ttl = 1000 * 60 * 60 * 24;

    public static String createJwt(User user){
        if (user == null || user.getEmail() == null){
            return null;
        }
        long current = System.currentTimeMillis();
        Date present = new Date(current);
        JwtBuilder builder = Jwts.builder()
                .claim("name", user.getUsername())
                .claim("email", user.getEmail())
                .setIssuedAt(present)
                .signWith(SignatureAlgorithm.HS256, key);
        if (ttl > 0){
            builder.setExpiration(new Date(current + ttl));
        }
        return builder.compact();
    }

    public static Claims parseJwt(String jwt) throws Exception {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        } catch (Exception e){
            log.error("token无效或过期");
            throw new Exception("token无效或过期");
        }
    }
}
