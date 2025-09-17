package com.cfs.bookMyShow.security;

import com.cfs.bookMyShow.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secretkey}")
    private  String   jwtSecretKey;

    public SecretKey  generateSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public  String generateAccessToken(User  user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim( "Name ",user.getName().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(generateSecretKey())
                .compact();
    }

    public String getUserNameFromToken(String token) {

        Claims claims =  Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return  claims.getSubject();
    }
}
