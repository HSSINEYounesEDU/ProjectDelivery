package com.BlogsProject.Authentication.Controller.AuthenticationServices;

import com.BlogsProject.Authentication.Email.PasswordRecovery.RecoveryTokenService;
import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import com.BlogsProject.Authentication.Security.Jwt.JwtGenerator;
import com.BlogsProject.Authentication.Security.Jwt.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableAsync
@Slf4j       //logs in terminal
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtGenerator jwtGenerator;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final RecoveryTokenService recoveryTokenService;

    private final BCryptPasswordEncoder passwordEncoder;


    @Async
    public void sendRecoveryMail(RegistrationRequest request) throws Exception {
        recoveryTokenService.sendRecoveryMail(request.getEmail());
    }
    public AuthenticationResponse register(RegistrationRequest request) throws Exception {
        Optional<User> user0 = userRepository.findUserByEmail(request.getEmail());
        Optional<User> user1 = userRepository.findUserByEmail(request.getEmail());
        if ( !user0.isPresent() && !user1.isPresent() ){
            User user = User.builder()
                    .email(request.getEmail())
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .phonenumber(request.getPhonenumber())
                    .role(request.getRole())
                    .address(request.getAddress())
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .archived(false)
                    .build();

            userRepository.save(user);

            String token = jwtGenerator.generateToken(user);

            sendRecoveryMail(request);

            return AuthenticationResponse.builder()
                    .activeToken(token)
                    .build();
        }
        throw new Exception("User registration failed: User already exists");     //status 403
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }
        catch (Exception e){
            User user = userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            log.error(e.getMessage());
            throw new UsernameNotFoundException("User not found !");
        }
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        String token = jwtGenerator.generateToken(user);
        return AuthenticationResponse.builder()
                .activeToken(token)
                .uid(user.getUid())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phonenumber(user.getPhonenumber())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    public AuthenticationResponse refreshToken(HttpServletRequest request) {

        final String authHeader = request.getHeader("Authorization");
        if( authHeader==null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        final String authToken = authHeader.substring(7);
        final String registrationNumber;
        try{
            registrationNumber = jwtService.extractRegistrationNumber(authToken);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new UsernameNotFoundException("User not found !");
        }

        if(!jwtService.isTokenValid(authToken)){
            User user = userRepository.findUserByEmail(registrationNumber)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found !"));
            final String newToken = jwtGenerator.generateToken(user);
            AuthenticationResponse.builder()
                    .activeToken(newToken)
                    .build();

            return AuthenticationResponse.builder()
                    .activeToken(newToken)
                    .uid(user.getUid())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .phonenumber(user.getPhonenumber())
                    .email(user.getEmail())
                    .build();
        }
        return null;
    }
}
