package com.BlogsProject.Authentication.Security.Jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Data
@Configuration
public class JwtConfiguration {

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Value("${JWT_EXPIRATION_TIME_HOURS}")
    private long jwtExpirationTime;

    public SecretKey getHashedSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
