package com.BlogsProject.Authentication.Security.Jwt;

import com.BlogsProject.Authentication.Entity.User;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private final JwtConfiguration jwtConfiguration;

    public String generateToken(User user){

        return Jwts
                .builder()
                .claim("uid", user.getUid())
                .claim("firstname", user.getFirstname())
                .claim("lastname", user.getLastname())
                .claim("email", user.getEmail())
                .claim("phonenumber", user.getPhonenumber())
                .claim("accountNonExpired", user.isAccountNonExpired())
                .claim("accountNonLocked", user.isAccountNonLocked())
                .claim("credentialsNonExpired", user.isCredentialsNonExpired())
                .claim("enable", user.isEnabled())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(jwtConfiguration.getJwtExpirationTime())))
                .signWith(jwtConfiguration.getHashedSecretKey())
                .compact();

    }
}
