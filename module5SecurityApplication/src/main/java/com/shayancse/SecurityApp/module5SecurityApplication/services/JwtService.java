package com.shayancse.SecurityApp.module5SecurityApplication.services;

import com.shayancse.SecurityApp.module5SecurityApplication.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {
    //Two methods are needed -> first one to create the JWT Token & second one to verify it

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    //Creation of JWT(Json Web Token)
    public String generateToken(User user) { //Make sure the User is coming from entities
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", Set.of("ADMIN", "USER"))
                .issuedAt(new Date()) //Current time
                .expiration(new Date(System.currentTimeMillis() + 1000*60*15)) //Expiration time -> after 1 minute
                //Upto this, these are the payloads subject, claims, issuedAt, expiration
                .signWith(getSecretKey())
                .compact();
    }

    //Verification of the Jwt Token
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()//Just making the parser available here
                .verifyWith(getSecretKey())
                .build()
                //Upto this we are building the parser -> like how to parse the token
                .parseSignedClaims(token) //parsing is done here
                .getPayload();

        return Long.valueOf(claims.getSubject()); //we are returning the subject means the user id

    }

}
