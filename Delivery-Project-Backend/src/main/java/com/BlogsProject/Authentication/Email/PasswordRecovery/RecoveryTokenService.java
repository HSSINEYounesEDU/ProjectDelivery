package com.BlogsProject.Authentication.Email.PasswordRecovery;

import com.BlogsProject.Authentication.Email.EmailDetails;
import com.BlogsProject.Authentication.Email.EmailService;
import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import com.BlogsProject.Authentication.Security.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class RecoveryTokenService {

    private final EmailService emailService;

    private final UserRepository userRepository;

    private final RecoveryTokenGenerator recoveryTokenGenerator;

    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder;


    public PasswordRecoveryResponse sendRecoveryMail(String email) throws Exception{

        Optional<User> opUser = userRepository.findUserByEmail(
                email);

        if (opUser.isPresent()) {
            User user = opUser.get();
            String token = recoveryTokenGenerator.generateToken(user);   //token can be coded using secret key.
            String link = "http://localhost:4200/password-change?token=" + token;
            EmailDetails mail = EmailDetails.builder()
                    .recipient(user.getEmail())
                    .subject("Password recovery.")
                    .msgBody("A request to change you ContainerTracker account password has been made.\n\n Recovery link: " + link)
                    .build();
            emailService.sendSimpleMail(mail);
            return new PasswordRecoveryResponse(200, "Email sent successfully.");
        }
        if (!opUser.isPresent()) {
            return new PasswordRecoveryResponse(500, "No user with such an email.");
        }
        return null;
    }

    public PasswordRecoveryResponse changeAccountPassword(PasswordChangeRequest request) throws Exception{
        String password = request.getPassword();
        String token = request.getToken();
        String registrationNumber = jwtService.extractRegistrationNumber(token);
        try{
            if (!jwtService.isTokenExpired(token)){
                log.error("token is valid !");
                Optional<User> opUser = userRepository.findUserByEmail(registrationNumber);
                if (opUser.isPresent()){
                    User user = opUser.get();
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                }
                return new PasswordRecoveryResponse(200, "Password changed successfully.");
            }
        }
        catch (Exception e) {                   // MUST LEARN TO HANDLE EXCEPTIONS
            return new PasswordRecoveryResponse(500, "Recovery token has been expired.");
        }
        return null;
    }
}
