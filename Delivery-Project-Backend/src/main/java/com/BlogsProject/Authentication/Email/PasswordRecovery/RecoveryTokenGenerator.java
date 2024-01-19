package com.BlogsProject.Authentication.Email.PasswordRecovery;


import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Security.Jwt.JwtConfiguration;
import io.jsonwebtoken.Jwts;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@Builder
@RequiredArgsConstructor
@Component

public class RecoveryTokenGenerator {

    private final JwtConfiguration jwtConfiguration;

    private final RecoveryTokenConfiguration recoveryTokenConfiguration;

    public String generateToken(User user){
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(recoveryTokenConfiguration.getExpirationTime())))
                .signWith(jwtConfiguration.getHashedSecretKey())
                .compact();

    }
}
