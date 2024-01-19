package com.BlogsProject.Authentication.Email.PasswordRecovery;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passwordRecoveryController")
@RequiredArgsConstructor

public class PasswordRecoveryController {

    private final RecoveryTokenService recoveryTokenService;


    @GetMapping ("/send-recovery-mail/{email}")
    public PasswordRecoveryResponse sendRecoveryMail(@PathVariable("email") String email) throws Exception{
        PasswordRecoveryResponse status = recoveryTokenService.sendRecoveryMail(email);
        return status;
    }

    @PostMapping("/change-account-password")
    public PasswordRecoveryResponse changeAccountPassword(@RequestBody PasswordChangeRequest request) throws Exception {
        PasswordRecoveryResponse status = recoveryTokenService.changeAccountPassword(request);
        return status;
    }
}
