package com.example.restdemo.security;

import com.example.restdemo.domain.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    // private static final String jwtSecret = "xhs82jskdskncKCJSq8129shcnxz9632";
    static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    static String jwtSecret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    private static final long tokenExpiration = 86400000;
    static SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserName())
                .claim("role", user.getRole().name())
                .claim("userId", user.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
