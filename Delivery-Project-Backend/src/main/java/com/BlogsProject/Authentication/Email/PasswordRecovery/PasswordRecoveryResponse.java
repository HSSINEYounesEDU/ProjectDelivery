package com.BlogsProject.Authentication.Email.PasswordRecovery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PasswordRecoveryResponse {

    private int status;

    private String details;
}
