package com.BlogsProject.Authentication.Security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserDetailsService userDetailsService;
    private final JwtConfiguration jwtConfiguration;


    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtConfiguration.getHashedSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractRegistrationNumber(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public UserDetails extractUserDetails(String token){
        String username = extractRegistrationNumber(token);
        return userDetailsService.loadUserByUsername(username);
    }

    public Date extractExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        Date expirationDate = extractExpirationDate(token);
        Date actualDate = new Date(System.currentTimeMillis());
        return expirationDate.before(actualDate);
    }

    public boolean isTokenValid(String token){
        String extractedUsername = extractRegistrationNumber(token);
        String loadedUsername = userDetailsService.loadUserByUsername(extractedUsername).getUsername();
        return extractedUsername.equals(loadedUsername) && !isTokenExpired(token);
    }
}
