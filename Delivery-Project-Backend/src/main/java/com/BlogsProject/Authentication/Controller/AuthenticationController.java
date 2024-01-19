package com.BlogsProject.Authentication.Controller;

import com.BlogsProject.Authentication.Controller.AuthenticationServices.AuthenticationRequest;
import com.BlogsProject.Authentication.Controller.AuthenticationServices.AuthenticationResponse;
import com.BlogsProject.Authentication.Controller.AuthenticationServices.AuthenticationService;
import com.BlogsProject.Authentication.Controller.AuthenticationServices.RegistrationRequest;
import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import com.BlogsProject.Authentication.Security.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authenticationController")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) throws Exception {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }


    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request){
        authService.refreshToken(request);
    }
}
