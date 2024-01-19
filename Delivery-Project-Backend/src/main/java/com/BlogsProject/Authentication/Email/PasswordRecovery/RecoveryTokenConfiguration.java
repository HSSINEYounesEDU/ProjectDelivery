package com.BlogsProject.Authentication.Email.PasswordRecovery;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration

public class RecoveryTokenConfiguration {

    @Value("${PASSWORD_RECOVERY_TOKEN_TIME_HOURS}")
    private long expirationTime;

}
